package com.spring.cucumber.services;

import java.util.List;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import com.spring.cucumber.models.User;

public interface UserService {
	List<User> getAllUsers() throws NotFoundException;
	User createUser(User user);
	User modifyUser(User user, String username) throws NotFoundException;
	boolean deleteUser(String username) throws NotFoundException;
	User getUserByUsername(String username) throws NotFoundException;
}