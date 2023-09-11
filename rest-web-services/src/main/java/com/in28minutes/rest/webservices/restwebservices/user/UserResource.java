package com.in28minutes.rest.webservices.restwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;


@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUserById(@PathVariable Integer id) {
		User user = userDaoService.findById(id);
		
		if (user == null) throw new UserNotFoundException("id: " + id);
		
		return user;
	}
	
	
	// since we already set the id to be auto-incremental
	// no need to pass in the id parameter, just the name and birthDate are ok
	
	// after set the return ResponseEntity.created(null).build(), the status code would be 201
	// since in HTTP, 201 stands for successfully created
	// can check the code in created method
	
	// Then we change the return to ResponseEntity.created(location).build(), where location is a URI Object
	// After we've done that,  we can see in the response header, there's a message showing the location of the newly added user
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		userDaoService.deleteById(id);
	}
}
