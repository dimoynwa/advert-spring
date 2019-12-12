package com.sirma.advertisement.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.repository.UserRepository;
import com.sirma.advertisement.api.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserInfo activateUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);

		return optionalUser.map(u -> {
			u.setActive(true);
			User user = userRepository.save(u);

			return new UserInfo(user);
		}).orElseThrow(() -> ResourceNotFoundException.userByIdNotFound(userId));
	}

	@Override
	public UserInfo blockUser(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		return optionalUser.map(u -> {
			u.setActive(false);
			User user = userRepository.save(u);

			return new UserInfo(user);
		}).orElseThrow(() -> ResourceNotFoundException.userByIdNotFound(userId));
	}

}
