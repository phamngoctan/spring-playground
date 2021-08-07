package com.playground.spring.springrestful.user;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	// Implicit constructor injection
	public UserDaoService service;
	@Autowired
	public UserResource(UserDaoService service) {
		this.service = service;
	}
//
//	@Autowired
//	public UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable Long id) {
		User user = service.find(id)
				.orElseThrow(() -> new UserNotFoundException(MessageFormat.format("User with id {0}", id)));
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = 
				WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(createdUser.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(createdUser);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		User deletedUser = service.deleteById(id);
		if (deletedUser == null) {
			throw new UserNotFoundException("id-"+ id);
		}
	}
	
}
