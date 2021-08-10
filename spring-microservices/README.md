# How to start the whole services to test
- naming-server: start this one first, it is the service discovery of the whole system.
- currency-exchange-service: we can start several instances by changing the port in application.properties file. I already disabled the devtool to make it easy. You can enable it later.
- currency-conversion-service: one instance is enough to prove.
- api-gateway: this is the gateway for the client to interact with. All the other services should be in the internal network and should not be exposed to the outside world. Check the urls for testing the service.

