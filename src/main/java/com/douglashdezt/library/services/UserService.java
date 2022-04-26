package com.douglashdezt.library.services;

import java.util.List;

import com.douglashdezt.library.models.entities.User;

public interface UserService {
	void insert(User user);
	void delete(String username);
	User getOneById(String username);
	List<User> getAll();
}
