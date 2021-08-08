package com.playground.spring.springrestful.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.playground.spring.springrestful.user.post.Post;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Past(message = "birth day should be in the past")
	private Date birthday;
	
	@Size(min = 2, message = "Name should have at least two characters")
	private String name;
	
	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	public User() {
		super();
	}

	public User(Long id, Date birthday, String name) {
		super();
		this.id = id;
		this.birthday = birthday;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, birthday=%s, name=%s]", id, birthday, name);
	}

}
