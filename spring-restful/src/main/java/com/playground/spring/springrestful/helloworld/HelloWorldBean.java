package com.playground.spring.springrestful.helloworld;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class HelloWorldBean {

	private String message;
	private ZonedDateTime createdDate;

	public HelloWorldBean(String string) {
		this.message = string;
		this.createdDate = ZonedDateTime.now(ZoneId.of("Z"));
	}

	public String getMessage() {
		return message;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}
	
}
