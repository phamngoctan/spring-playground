package com.playground.spring.springrestful.helloworld;

import java.text.MessageFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public String ping() {
		return "Hello world";
	}
	
	@GetMapping(path = "/ping2")
	public String ping2() {
		return "Hello world 2";
	}
	
	@GetMapping(path = "/ping3")
	public HelloWorldBean ping3() {
		return new HelloWorldBean("Hello world 3");
	}
	
	@GetMapping(path = "/ping/{name}/{age}")
	public HelloWorldBean ping3(@PathVariable String name,
			@PathVariable(name = "age") String ag) {
		return new HelloWorldBean(MessageFormat.format("Hello {0} {1}", name, ag));
	}
	
}
