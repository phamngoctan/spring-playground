# How to start the whole services to test
- naming-server: start this one first, it is the service discovery of the whole system.
- currency-exchange-service: we can start several instances by changing the port in application.properties file. I already disabled the devtool to make it easy. You can enable it later.
- currency-conversion-service: one instance is enough to prove.
- api-gateway: this is the gateway for the client to interact with. All the other services should be in the internal network and should not be exposed to the outside world. Check the urls for testing the service.

# Using docker to start all services
Go to the docker-compose folder and run docker-componse up. That's it!

```
docker-compose up
```

# Testing cheat-sheet

```
curl --location --request GET 'http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10'[1-20] --header 'Content-Type: application/json' --data-raw '{
    "birthday": "2000-08-03T03:28:06.822+00:00",
    "name": "John"
}'

```
