package com.sirma.advertisement.api.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.entity.JobPost;
import com.sirma.advertisement.api.entity.JobPostSkill;
import com.sirma.advertisement.api.entity.JobType;
import com.sirma.advertisement.api.entity.Skill;
import com.sirma.advertisement.api.entity.SkillLevel;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.model.CreateJobPostRequest;
import com.sirma.advertisement.api.model.JobPostInfo;
import com.sirma.advertisement.api.model.PagedResponse;
import com.sirma.advertisement.api.model.SkillLevelModel;
import com.sirma.advertisement.api.repository.JobPostRepository;
import com.sirma.advertisement.api.repository.JobTypeRepository;
import com.sirma.advertisement.api.repository.SkillRepository;
import com.sirma.advertisement.api.repository.UserRepository;
import com.sirma.advertisement.api.service.FileService;
import com.sirma.advertisement.api.service.JobPostService;

@Service
public class JobPostServiceImpl implements JobPostService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private JobTypeRepository jobTypeRepository;
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private FileService fileService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public JobPostInfo createJobPost(Long posterId, CreateJobPostRequest request, MultipartFile avatar) throws Exception {
		JobPost post = new JobPost(request);
		Optional<User> user = userRepository.findById(posterId);
		if(!user.isPresent()) {
			throw ResourceNotFoundException.userByIdNotFound(posterId);
		}
		
		post.setPoster(user.get());
		post.setActive(true);
		
		List<String> skillNames = request.getSkills().stream().map(s -> {return s.getSkill().getName();}).collect(Collectors.toList());
		
		List<Skill> skillsFound = skillRepository.findByNameIn(skillNames);
		
		Map<String, Skill> skillsMap = skillsFound.stream().collect(
                Collectors.toMap(s -> s.getName(), s -> s));
		
		Set<JobPostSkill> jobPostSkills = new HashSet<>();
		
		for(SkillLevelModel s : request.getSkills()) {
			if(skillsMap.containsKey(s.getSkill().getName())) {
				jobPostSkills.add(new JobPostSkill(null, post, skillsMap.get(s.getSkill().getName()), SkillLevel.valueOf(s.getSkillLevel())));
			} else {
				jobPostSkills.add(new JobPostSkill(null, post, new Skill(s.getSkill()), SkillLevel.valueOf(s.getSkillLevel())));
			}
		}
		
		post.setSkills(jobPostSkills);
		
		Optional<JobType> optJobType = jobTypeRepository.findByName(request.getJobType().getName());
		
		if(!optJobType.isPresent()) {
			JobType newJobType = new JobType(request.getJobType());
			newJobType = jobTypeRepository.save(newJobType);
			
			post.setJobType(newJobType);
		} else {
			post.setJobType(optJobType.get());
		}
		
		
		String avatarName = null;
		if(avatar != null && !avatar.isEmpty()) {
			avatarName = fileService.storeAvatar(avatar);
		}
		post.setAvatar(avatarName);
		
		post = jobPostRepository.save(post);
		
		return new JobPostInfo(post);
	}

	@Override
	public PagedResponse<JobPostInfo> myJobPosts(Long posterId, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdAt");
		
		Page<JobPost> jobPosts = jobPostRepository.findByPosterId(posterId, pageable);
		
		if(jobPosts.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), jobPosts.getNumber() + 1,
            		jobPosts.getSize(), jobPosts.getTotalElements(), jobPosts.getTotalPages(), jobPosts.isLast());
        }
		
		List<JobPostInfo> postsInfo = jobPosts.get().map(post -> new JobPostInfo(post)).collect(Collectors.toList());
		
		return new PagedResponse<JobPostInfo>(postsInfo, jobPosts.getNumber() + 1,
				jobPosts.getSize(), jobPosts.getTotalElements(), jobPosts.getTotalPages(), jobPosts.isLast());
	}

//	/@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public JobPostInfo updateJobPost(Long posterId, Long postId, JobPostInfo jobPost, MultipartFile avatar) throws Exception {
		Optional<JobPost> optionalPost = jobPostRepository.findById(postId);
		if(!optionalPost.isPresent()) {
			throw ResourceNotFoundException.jobPostByIdNotFound(postId);
		}
		JobPost post = optionalPost.get();
		
		if(!post.getPoster().getId().equals(posterId)) {
			throw new RuntimeException("You can update only your posts.");
		}
		
		post.setActive(jobPost.getActive());
		post.setCompanyName(jobPost.getCompanyName());
		post.setShortDescription(jobPost.getShortDescription());
		post.setDescription(jobPost.getDescription());
		
		Optional<JobType> optJobType = jobTypeRepository.findByName(jobPost.getJobType().getName());
		if(optJobType.isPresent()) {
			post.setJobType(optJobType.get());
		} else {
			JobType newJobType = new JobType(jobPost.getJobType().getName());
			newJobType = jobTypeRepository.save(newJobType);
			post.setJobType(newJobType);
		}

		List<String> skillNames = jobPost.getSkills().stream().map(s -> {return s.getSkill().getName();}).collect(Collectors.toList());
		
		List<Skill> skillsFound = skillRepository.findByNameIn(skillNames);
		
		Map<String, Skill> skillsMap = skillsFound.stream().collect(
                Collectors.toMap(s -> s.getName(), s -> s));
		
		Set<JobPostSkill> jobPostSkills = new HashSet<>();
		
		for(SkillLevelModel s : jobPost.getSkills()) {
			if(skillsMap.containsKey(s.getSkill().getName())) {
				jobPostSkills.add(new JobPostSkill(null, post, skillsMap.get(s.getSkill().getName()), SkillLevel.valueOf(s.getSkillLevel())));
			} else {
				jobPostSkills.add(new JobPostSkill(null, post, new Skill(s.getSkill()), SkillLevel.valueOf(s.getSkillLevel())));
			}
		}
		
		post.setSkills(jobPostSkills);
		
		if(jobPost.getImageChanged()) {
			String avavarName = fileService.storeAvatar(avatar);
			post.setAvatar(avavarName);
		}
		
		return new JobPostInfo(jobPostRepository.save(post));
	}

	@Override
	public PagedResponse<JobPostInfo> getAllPosts(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdAt");
		Page<JobPost> jobPosts = jobPostRepository.findAll(pageable);
		
		if(jobPosts.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), jobPosts.getNumber() + 1,
            		jobPosts.getSize(), jobPosts.getTotalElements(), jobPosts.getTotalPages(), jobPosts.isLast());
        }
		
		List<JobPostInfo> postsInfo = jobPosts.get().map(post -> new JobPostInfo(post)).collect(Collectors.toList());
		
		return new PagedResponse<JobPostInfo>(postsInfo, jobPosts.getNumber() + 1,
				jobPosts.getSize(), jobPosts.getTotalElements(), jobPosts.getTotalPages(), jobPosts.isLast());
	}

	@Override
	public JobPostInfo getPostById(Long postId) {
		Optional<JobPost> optionalPost = jobPostRepository.findById(postId);
		if(!optionalPost.isPresent()) {
			throw ResourceNotFoundException.jobPostByIdNotFound(postId);
		}
		return new JobPostInfo(optionalPost.get());
	}

	@Override
	public JobPostInfo deletePost(Long posterId, Long postId) {
		Optional<JobPost> optionalPost = jobPostRepository.findById(postId);
		if(!optionalPost.isPresent()) {
			throw ResourceNotFoundException.jobPostByIdNotFound(postId);
		}
		JobPost post = optionalPost.get();
		if(!posterId.equals(post.getPoster().getId())) {
			throw new RuntimeException("You can delete only your posts.");
		}
		jobPostRepository.deleteById(postId);
		return new JobPostInfo(post);
	}
	
//	private void saveImage(MultipartFile file, JobPost postEntity) throws IOException {
//		InformationFile image = new InformationFile(null, file.getName(), file.getContentType(), true, file.getBytes(), postEntity);
//		informationFileRepository.save(image);
//	}

	
}
