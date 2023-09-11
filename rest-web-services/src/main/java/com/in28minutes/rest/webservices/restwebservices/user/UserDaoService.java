package com.in28minutes.rest.webservices.restwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> userList = new ArrayList<>();
	
	private static int userCount = 0;
	
	static {
		userList.add(new User(++ userCount, "Adam", LocalDate.now().minusYears(30)));
		userList.add(new User(++ userCount, "Eve", LocalDate.now().minusYears(25)));
		userList.add(new User(++ userCount, "Jim", LocalDate.now().minusYears(10)));
	}
	
	
	public List<User> findAll() {
		return userList;
	}
	
	public User findById(Integer id) {
//		Predicate<? super User> predicate = user -> user.getId().equals(id); 
//		return userList.stream().filter(predicate).findFirst().get();
		
		for (User user: userList) {
			if (user.getId().equals(id)) return user;
		}
		return null;
	}
	
	public User save(User user) {
		user.setId(++ userCount);
		userList.add(user);
		return user;
	}
	
	public void deleteById(Integer id) {
		for (User user: userList) {
			if (user.getId().equals(id)) userList.remove(user);
		}
	}
	
	
}
