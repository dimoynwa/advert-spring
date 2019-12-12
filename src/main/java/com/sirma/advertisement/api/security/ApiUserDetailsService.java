package com.sirma.advertisement.api.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sirma.advertisement.api.entity.User;
import com.sirma.advertisement.api.exception.ResourceNotFoundException;
import com.sirma.advertisement.api.repository.UserRepository;

@Service
public class ApiUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

        return UserPrincipal.fromUser(user);
    }
	
	@Transactional
    public UserDetails loadUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.fromUser(user);
    }
	
}
