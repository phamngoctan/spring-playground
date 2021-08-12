# How to create the docker image

Be aware to setup the correct JAVA_HOME in the system path.

## Using maven pluggin provided by Spring boot to build an image

```
mvn spring-boot:build-image -DskipTests
```

## Using dockerfile to build an image

The size of docker image is not so different. 310MB for dockerfile building and 312MB for using maven pluggin approach.

Next comparison, about the layering of app, it seems that the maven pluggin built also supports the layering inside the execution jar file. If this is the case, I would prefer using maven pluggin because it is maintained by Spring team.

```
docker build -t spring-playground/currency-exchange-service .
docker run --name="currency-exchange-service" -p 8000:8000 -t spring-playground/currency-exchange-service
```
