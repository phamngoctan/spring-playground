

## How to check the app is ready
curl http://localhost:8080/ping

## Comparing Spring Boot vs JavaEE in term of minimal project to build up a RESTful WebService

Both stacks can support quickly setup. Spring Initialzr is the place to quickly build where JavaEE, I used the maven archetype provided by Adam Bien (https://github.com/phamngoctan/javaee-playground/tree/master/hello-world/hello-world-javaee8).

In term of debugging the app, Spring boot is really simple, just like the debugging the JavaSE app. I really like it. For JavaEE, it is a long way to achieve it. You have to enable the debugged port in the application server, then using IDE to do the remote debugging.

## How the RESTful APIs work in Spring

- What is dispatcher servlet?
- Who is configuring dispatcher servlet?
- What does dispatcher servlet do?
- How does the HelloWorldBean object get converted to JSON?
- Who is configuring the error mapping?

Answers

- Mapping servlet: dispatcherServlet to [/]  It knows all the mappings and it forward to the corresponding handler for each coming request

	- When there is a request coming to "curl http://localhost:8080/ping3"
	
	- Mapped "/ping3, method GET" onto om.playground.spring.springrestful.PingController.java method ping3
	
	- When there is a response ready, in the @RestController, it has the annotation @ResponseBody. It marks the output Java POJO for the Message Converter internally using Jackson dependencies by default to convert to the output.

- Mapping the error using ErrorMvcAutoConfiguration


All of the above configuration is done by SpringBootAutoConfiguration. It checks the classpath the existence of pre-defined classes

## Bean types??? and its scope???
## Exception handling in Spring

```
@ControllerAdvice
@RestController
and extends ResponseEntityExceptionHandler
```

## Validation API
Spring uses hibernate-validator as the implementation for javax validation API

