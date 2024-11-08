package com.example.ali_nino.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ali_nino.dao.entity.User;
import com.example.ali_nino.dao.repository.UserRepository;
import com.example.ali_nino.exception.NotFoundException;

@Component
public class UserHelper {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});
		return user;
	}
}
