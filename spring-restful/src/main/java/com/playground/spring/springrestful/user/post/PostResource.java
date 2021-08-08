package com.playground.spring.springrestful.user.post;

import java.net.URI;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.playground.spring.springrestful.user.User;
import com.playground.spring.springrestful.user.UserNotFoundException;
import com.playground.spring.springrestful.user.UserRepository;

@RestController
public class PostResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users/{id}/posts")
	public Set<Post> getAllPosts(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user id - " + id));
		return user.getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(
			@PathVariable Long id,
			@Valid @RequestBody Post post) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user id - " + id));
		post.setUser(user);
		Post createdPost = postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(createdPost.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(createdPost);
	}
	
}
