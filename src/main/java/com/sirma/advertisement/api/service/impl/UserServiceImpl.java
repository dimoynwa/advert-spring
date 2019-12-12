package com.sirma.advertisement.api.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.advertisement.api.config.MailSheduledSender;
import com.sirma.advertisement.api.entity.JobPost;
import com.sirma.advertisement.api.entity.Mail;
import com.sirma.advertisement.api.entity.PasswordResetToken;
import com.sirma.advertisement.api.entity.Role;
import com.sirma.advertisement.api.entity.RoleName;
import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.entity.UserImage;
import com.sirma.advertisement.api.exception.ResourceAlreadyExistsException;
import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.model.ChangePasswordRequest;
import com.sirma.advertisement.api.model.JobPostInfo;
import com.sirma.advertisement.api.model.PagedResponse;
import com.sirma.advertisement.api.model.SignUpRequest;
import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.repository.JobPostRepository;
import com.sirma.advertisement.api.repository.MailRepository;
import com.sirma.advertisement.api.repository.PasswordResetTokenRepository;
import com.sirma.advertisement.api.repository.RoleRepository;
import com.sirma.advertisement.api.repository.UserImageRepository;
import com.sirma.advertisement.api.repository.UserRepository;
import com.sirma.advertisement.api.service.FileService;
import com.sirma.advertisement.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserImageRepository userImageRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailRepository mailRepository;
	
	@Autowired
	private MailSheduledSender mailScheduledSender;
	
	@Autowired
	private FileService fileService;
	
	@Override
	@Transactional
	public UserInfo registerUser(SignUpRequest request, MultipartFile avatar) throws Exception {

		if (userRepository.existsByUserName(request.getUserName())) {
			throw ResourceAlreadyExistsException.userByUserName(request.getUserName());
		}

		if (userRepository.existsByEmail(request.getEmail())) {
			throw ResourceAlreadyExistsException.userByEmail(request.getEmail());
		}

		User user = new User(request);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByRoleName(RoleName.valueOf(request.getRole()))
				.orElseThrow(() -> ResourceNotFoundException.roleByNameNotFound(request.getRole()));
		user.setRoles(Collections.singleton(userRole));

		String avatarPath = fileService.storeAvatar(avatar);
		
		user.setAvatar(avatarPath);
		user = userRepository.save(user);
		
//		if(avatar != null) {
//			saveImage(avatar, user);
//		}
		
		PasswordResetToken token = new PasswordResetToken();
		token.setExpiryDate(new Date());
		token.setExpiryDate(30);
		token.setUser(user);
		token.setToken(UUID.randomUUID().toString());
		
		token = passwordResetTokenRepository.save(token);

		passwordResetTokenRepository.save(token);
		
		Mail mail = mailScheduledSender.activateUserMail(user, token);
		mailRepository.save(mail);
		
		return new UserInfo(user);
	}

	@Override
	public PagedResponse<UserInfo> getAllUsers(int page, int size) {
		
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdAt");
		
		Page<User> users = userRepository.findAll(pageable);
		
		if(users.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), users.getNumber() + 1,
            		users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
        }
		
		List<UserInfo> usersInfo = users.get().map(user -> new UserInfo(user)).collect(Collectors.toList());
		
		return new PagedResponse<UserInfo>(usersInfo, users.getNumber() + 1,
        		users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
	}
	
	@Override
	public UserInfo getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
	          () ->  ResourceNotFoundException.userByIdNotFound(id)
	    );
		return new UserInfo(user);
	}
	
//	private void saveImage(MultipartFile file, User userEntity) throws IOException {
//		UserImage image = new UserImage(null, file.getName(), file.getContentType(), file.getBytes(), userEntity);
//		userImageRepository.save(image);
//	}

	@Override
	@Transactional
	public byte[] getUserAvatar(Long userId) {
		Optional<UserImage> avatar = userImageRepository.findByUserId(userId);
		if(!avatar.isPresent()) {
			return new byte[0];
		}
		
		return avatar.get().getData();
	}

	@Override
	public UserInfo updateMyUser(Long id, UserInfo body) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw ResourceNotFoundException.userByIdNotFound(id);
		}
		
		User userFound = user.get();
		userFound.setFirstName(body.getFirstName());
		userFound.setLastName(body.getLastName());
		checkEmail(body.getEmail());
		userFound.setEmail(body.getEmail());
		checkUsername(body.getUserName());
		userFound.setUserName(body.getUserName());
		
		return new UserInfo(userRepository.save(userFound));
	}

	@Override
	public UserInfo changePassword(Long id, ChangePasswordRequest request) {
		Optional<User> optional = userRepository.findById(id);
		if(!optional.isPresent()) {
			throw ResourceNotFoundException.userByIdNotFound(id);
		}
		
		User user = optional.get();
		if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new RuntimeException("Old password doesn't match.");
		}
		String newPassword = passwordEncoder.encode(request.getPassword());
		user.setPassword(newPassword);
		
		return new UserInfo(userRepository.save(user));
	}

	@Override
	public void checkUsername(String username) {
		if(userRepository.existsByUserName(username)) {
			throw ResourceAlreadyExistsException.userByUserName(username);
		}
	}

	@Override
	public void checkEmail(String email) {
		if(userRepository.existsByEmail(email)){
			throw ResourceAlreadyExistsException.userByEmail(email);
		}
	}

	@Override
	public UserInfo updateAvatar(Long id, MultipartFile avatar) throws Exception {
		Optional<User> optinalUser = userRepository.findById(id);
		if(!optinalUser.isPresent()) {
			throw ResourceNotFoundException.userByIdNotFound(id);
		}
		
		User user = optinalUser.get();
		
		String avatarPath = fileService.storeAvatar(avatar);
		
		user.setAvatar(avatarPath);
		user = userRepository.save(user);
		
		return new UserInfo(user);
	}

	@Override
	public UserInfo getUserByUserName(String username) {
		Optional<User> optionalUser = userRepository.findByUserNameOrEmail(username, null);
		if(!optionalUser.isPresent()) {
			throw ResourceNotFoundException.userByUsername(username);
		}
		return new UserInfo(optionalUser.get());
	}

	@Override
	public PagedResponse<JobPostInfo> getUserPostsByUsername(int page, int size, String username) {
		Optional<User> optionalUser = userRepository.findByUserNameOrEmail(username, null);
		if(!optionalUser.isPresent()) {
			throw ResourceNotFoundException.userByUsername(username);
		}
		
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdAt");
		Page<JobPost> posts = jobPostRepository.findByPosterId(optionalUser.get().getId(), pageable);
		
		if(posts.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), posts.getNumber() + 1,
            		posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
        }
		
		List<JobPostInfo> jobPostsInfo = posts.get().map(post -> new JobPostInfo(post)).collect(Collectors.toList());
		
		return new PagedResponse<JobPostInfo>(jobPostsInfo, posts.getNumber() + 1,
				posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
	}
	
}
