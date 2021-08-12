# spring-playground
Document for Spring is really well prepared, easy to search and apply.

# Spring framework
Spring core goal: to take control all the beans and there dependencies. It solve the problem of dependency injection, build loosely couple applications.

What else?

- Duplicated code. Spring JDBC, Spring MVC, Spring ORM, Spring JMS, Spring AOP, Spring Test


# Spring Boot

Spring Boot Goals:

- Enable building production ready for java application
- Provide common non-functionality features:
	- embedded servers
	- metrics
	- health checks 
	- externalized configuration

Without using Spring Boot, using Spring should have the following decisions:

- decide the needed dependencies, which version, compatible or not
- we need to implement the default exception handling
- implement complete Spring configuration file
- configure the web.xml:
	- dispatcher servlet
	- Spring security

## Spring Cloud config server

One centralized repo, other modules use file bootstrap.properties to config the uri to point to the Spring Cloud config server. :)

- support different profile.
- Spring cloud config server can connect to the git repo and populate the data in git repo to the interested modules.

```
CurrencyCalculationService    ExchangCurrencyService  LimitsService
                    \              |                  /
                          SpringCloudConfigServer
                                   |
                                   v
                                  Git
```

Spring cloud bus for dynamically changing the configuration at runtime for LimitsService when there is a change inside the git. Behind the scene, it uses the RabbitMQ for triggering the sync.

## Dynamic scale up and down

In version 2, we use different technologies:
- Spring cloud load balancer instead of Ribbon
- Spring cloud gateway instead of Zuul
- Resilience4j instead of Hystri

V1:
- Naming Server (Eureka) Netflix
	- Service registration
	- Service discovery
- Ribbon (client side load balancing) Netflix
- Feign (Easier REST clients) Netflix 

```
                              CurrencyCalculationService
                                           |
                                           v
                                        Ribbon   -> NamingServer
                        /                  |                              \            
      CurrencyExchangeService1     CurrencyExchangeService2      CurrencyExchangeService3
```

## API Gateway
Benefit
- Authentication, authorization and security
- Rate limits
- Fault tolerance
- Service aggregation

V1: Zuul API Gateway for the implementation

V2: Spring Cloud Gateway features:
- Matches on any request attributes
- Define predicates and filters
- Integrate with Spring Cloud Discovery Client (load balancing)
- Path Rewriting

Spring Cloud Gateway vs Zuul API Gateway:
- Zuul is built on servlet 2.5 (works with 3.x), using blocking APIs. It doesn't support any long lived connections, like websockets.
- Gateway is built on Spring Framework 5, Project Reactor and Spring Boot 2 using non-blocking APIs. Websockets are supported and it's a much better developer experience since it's tightly integrated with Spring.


## Visibility and monitoring

- Zipkin distributed tracing
- netflix API Gateway

```
CurrencyCalculationService    ExchangCurrencyService  LimitsService
                    \              |                  /
                                RabbitMQ
                                   |
                      ZipkinDistributedTracingServer
                                   |
                               Database
```

### Spring Cloud Sleuth
- Adds trace and span ids to the Slf4J
- servlet filter, rest template, scheduled actions, message channels, feign client, auto supported, each action behind will have the trace and span ids
- If spring-cloud-sleuth-zipkin is available then the app will generate and report Zipkin-compatible traces via <b>HTTP</b>
- Sleuth with Zipkin over RabbitMQ or Kafka: If you want to use RabbitMQ or Kafka instead of HTTP, add the spring-rabbit or spring-kafka dependency

## Fault tolerance
Hystrix in version 1 and in version 2, we use the resilient

- Retry and fallback method
- Circuit breaker and fallback method

	- Circuit breaker auto replies back to the client without calling the service inside the API.
	- It auto detect the failure service calls and auto return to the client the fallback response.
	- Good reference https://resilience4j.readme.io/docs/circuitbreaker.

- Ratelimit: maximum concurrent requests for a specific API
