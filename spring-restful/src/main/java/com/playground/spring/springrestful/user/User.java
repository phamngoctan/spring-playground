package com.playground.spring.springrestful.user;

import java.util.Date;

public class User {

	private Long id;
	private Date birthday;
	private String name;

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

	@Override
	public String toString() {
		return String.format("User [id=%s, birthday=%s, name=%s]", id, birthday, name);
	}

}