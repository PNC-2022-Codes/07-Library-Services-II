package com.douglashdezt.library.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.douglashdezt.library.models.entities.User;

@Service
public class UserServiceImpl implements UserService{

	private static Map<String, User> users = new HashMap<>();
	static {
		/*
		 * 	msambals0	Mariya		Sambals
			acopins1	Audy		Copins
			flawly2		Florella	Lawly
			hshevlane3	Holly		Shevlane
		 * */
		
		User user_1 = new User("msambals0", "Mariya", "Sambals");
		users.put(user_1.getUsername(), user_1);
		
		User user_2 = new User("acopins1", "Audy", "Copins");
		users.put(user_2.getUsername(), user_2);
		
		User user_3 = new User("flawly2", "Florella", "Sambals");
		users.put(user_3.getUsername(), user_3);
		
		User user_4 = new User("hshevlane3", "Holly", "Shevlane");
		users.put(user_4.getUsername(), user_4);
	}
	
	@Override
	public void insert(User user) {
		users.put(user.getUsername(), user);
	}

	@Override
	public void delete(String username) {
		users.remove(username);
	}

	@Override
	public User getOneById(String username) {
		return users.get(username);
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<>(users.values());
	}
	
}
