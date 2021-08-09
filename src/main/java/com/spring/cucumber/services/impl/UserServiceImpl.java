package com.spring.cucumber.services.impl;

import java.util.List;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cucumber.models.User;
import com.spring.cucumber.repositories.UserRepository;
import com.spring.cucumber.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		log.info("Get All Users"); 
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		log.info("Save User Service");
		return userRepository.save(user);
	}

	@Override
	public User modifyUser(User user, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String username) throws NotFoundException {
		log.info("User Deletion Initiated For {}",username);

		User user = userRepository.findByUsername(username)
						.orElseThrow(() ->  new NotFoundException(User.class,username));

		userRepository.deleteById(user.getId());

		log.info("User Deletion Success For {}",username);

		return true;
	}

	@Override
	public User getUserByUsername(String username) throws NotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException(User.class,username));
	}
	
}
