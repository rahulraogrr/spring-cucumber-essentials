package com.spring.cucumber.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<User> getAllUsers() throws NotFoundException{
		log.info("Get All Users");

		if(userRepository.findAll().isEmpty())
			throw new NotFoundException();

		return userRepository.findAll().stream().sorted().collect(Collectors.toList());
	}

	@Override
	public User createUser(User user) {
		log.info("Save User Service");
		return userRepository.save(user);
	}

	@Override
	public User modifyUser(User user, String username) throws NotFoundException {
		log.info("Modify User {}",username);

	    User modifyUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException(User.class,username));

		modifyUser.setUsername(user.getUsername());
		modifyUser.setPassword(user.getPassword());
		modifyUser.setFirstName(user.getFirstName());
		modifyUser.setMiddleName(user.getMiddleName());
		modifyUser.setLastName(user.getLastName());
		modifyUser.setDateOfBirth(user.getDateOfBirth());
		modifyUser.setEmail(user.getEmail());
		modifyUser.setMobile(user.getMobile());

		//Will handle a collection with cascade= all-delete-orphan was no longer referenced by the owning entity instance
		modifyUser.getAddress().clear();
		modifyUser.getAddress().addAll(user.getAddress());

		return userRepository.save(modifyUser);
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
