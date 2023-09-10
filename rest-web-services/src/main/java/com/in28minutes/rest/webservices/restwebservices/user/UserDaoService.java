package com.in28minutes.rest.webservices.restwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> userList = new ArrayList<>();
	
	static {
		userList.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		userList.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
		userList.add(new User(3, "Jim", LocalDate.now().minusYears(10)));
	}
	
	
	public List<User> findAll() {
		return userList;
	}
	
	public User findById(int id) {
//		Predicate<? super User> predicate = user -> user.getId().equals(id); 
//		return userList.stream().filter(predicate).findFirst().get();
		
		for (User user: userList) {
			if (user.getId().equals(id)) return user;
		}
		return null;
	}
	
	
}
