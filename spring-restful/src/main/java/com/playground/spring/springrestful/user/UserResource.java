package com.playground.spring.springrestful.user;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	public UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable Long id) {
		return service.find(id)
				.orElseThrow(() -> new UserNotFoundException(MessageFormat.format("User with id {0} not found", id)));
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(createdUser.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(createdUser);
	}
	
}
