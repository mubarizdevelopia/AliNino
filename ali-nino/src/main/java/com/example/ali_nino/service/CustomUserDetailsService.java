package com.example.ali_nino.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ali_nino.dao.entity.User;
import com.example.ali_nino.dao.repository.UserRepository;
import com.example.ali_nino.exception.NotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.build();
	}
}
