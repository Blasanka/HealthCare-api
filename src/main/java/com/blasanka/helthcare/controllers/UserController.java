package com.blasanka.helthcare.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blasanka.helthcare.database.DatabaseRef;
import com.blasanka.helthcare.models.User;

public class UserController {

	private Map<Long, User> users = DatabaseRef.getUsers();
	
	public UserController() {
	}
	
	public List<User> getUsers() {
		return new ArrayList<>(users.values());
	}
	
	public User getUser(long id) {
		return users.get(id);
	}

	public User addUser(User user) {
		user.setUserId(users.size() + 1L);
		users.put(user.getUserId(), user);
		return user;
	}
	
	public User updateUser(User user) {
		if (user.getUserId() <= 0) return null;
		users.put(user.getUserId(), user);
		return user;
	}
	
	public User removeUser(long id) {
		return users.remove(id);
	}
	
}
