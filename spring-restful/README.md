

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

## Hateoas - Hypermedia as the Engine of Application State
Attach neccessary link to the model before returning to the client

## Content negotiation - using request header - Accept: application/xml

## I18n - using request header - Accept-Language: en

## API documents - Open API 3.0 - Springdoc - this thing is amazing :)
```
localhost:8080/swagger-ui.html
```
For getting the json document:
```
curl localhost:8080/v3/api-docs
```

## Monitoring APIs with Spring Boot Actuator
Where to check the actuator
```
curl http://localhost:8080/actuator/
```
By default, just only three APIs are available. How to enable other? Add this property to the application.properties
```
management.endpoints.web.exposure.include=*
```

## Visualizing APIs with HAL Explorer - JSON Hypertext Application Language or HAL
HAL Explorer helps us check the APIs in an easy way. Even working quite well with the Hateoas. For non-technical guys, it would be best to use it. Really convenient without requiring any additional softwares installed.

Go to url localhost:8080 for the HAL Explorer checking

## Static Filtering the data returned back to client - using jackson @JsonIgnore 

## Dynamic Filtering the data returned back to client - using jackson MappingJacksonValue

## Versioning for APIs
- URI versioning (Twitter)
- Request parameter versioning (Amazon)
- Custom header versioning (Microsoft)
- Media type versioning (Accept: application/vnd.company.app-v2+json) (GitHub)

Factors affects the decision:

- URI pollution
- Misuse of HTTP Headers (HTTP headers are invented not for versioning :) )
- Caching
- Can we execute the request on the browser?
- API documentation

No perfect solution :)

## Spring security with basic authentication

## Spring with JPA
Using h2 database

```
# Access h2 database with JDBC url jdbc:h2:mem:testdb
http://localhost:8080/h2-console
```

## RESTful best practices
Richarson Maturity Model helps us this thing. It has 3 levels:

- Level 1: Expose SOAP web services in rest style
	
	- http://server/getPosts
	- http://server/deletePosts
	- http://server/doThis
	
- Level 2: Expose resources with proper URI
	
	- http://server/accounts
	- http://server/accounts/10
	Note: improper use of http methods

- Level 3: Level 2 + HATEOAS -> means data + next possible actions

Best practices:

- Consumer first (understand documents)


# Spring Cloud

## Spring Cloud config server

```
CurrencyCalculationService    ExchangCurrencyService  LimitsService
                    \              |                  /
                          SpringCloudConfigServer
                                   |
                                   v
                                  Git
```

## Dynamic scale up and down

- Naming Server (Eureka)
- Ribbon (client side load balancing)
- Feign (Easier REST clients)

```
                              CurrencyCalculationService
                                           |
                                           v
                                        Ribbon   -> NamingServer
                        /                  |                              \            
      CurrencyExchangeService1     CurrencyExchangeService2      CurrencyExchangeService3
```

## Visibility and monitoring

- Zipkin distributed tracing
- netflix API Gateway

## Fault tolerance

- Hystrix