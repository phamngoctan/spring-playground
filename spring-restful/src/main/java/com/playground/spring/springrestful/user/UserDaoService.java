package com.playground.spring.springrestful.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static Long userCount = 3L;
	static {
		users.addAll(List.of(
				new User(1L, new Date(), "John"),
				new User(2L, new Date(), "Peter"),
				new User(3L, new Date(), "Jim")
				));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public Optional<User> find(Long id) {
		return users.stream().filter(i -> i.getId().equals(id)).findAny();
	}

	public User deleteById(Long id) {
		Optional<User> userOpt = users.stream().filter(u -> u.getId().equals(id)).findAny();
		if (userOpt.isPresent()) {
			users.removeIf(u -> u.getId().equals(id));
			return userOpt.get();
		}
		return null;
	}
}

