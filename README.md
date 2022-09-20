# Project 2
A basic implementation of a microservice with Spring Boot and Spring Cloud.

This repo contains:
* [Config Server](#config-server)
* [Eureka Naming Server](#eureka-naming-server)
* [Currency Exchange Service](#currency-exchange-service)
* [Currency Conversion Service](#currency-conversion-service)
* [Distributed Tracing](#distributed-tracing)
* [API Gateway](#api-gateway)


## Config Server
Externalizing application configuration with Spring Cloud Config. 
This project configures Spring Cloud Config server with Git URL as the backend.
- Config repo URL: https://github.com/phaptrinh/Project2-config  
- Port: 8888

```
GET http://localhost:8888/currency-exchange-service/dev
```

## Eureka Naming Server
Using Spring Cloud Netflix Eureka discovery service to provide service registration and client-side load-balancing.
- Port: 8761


## Currency Exchange Service
Currency Exchange Service convert one currency to another currency. Conversion rates are stored in H2 database.
- Port: 8000, 8001
```
GET http://localhost:8000/currency-exchange/from/USD/to/VND
```

## Currency Conversion Service
Currency Conversion Service take an amount of currency and convert its value to another currency using Currency Exchange Service.
- Port: 8100, 8101
```
GET http://localhost:8100/currency-conversion/from/USD/to/VND/quantity/10000

GET http://localhost:8100/currency-conversion-feign/from/USD/to/VND/quantity/10000
```

## Distributed Tracing
Using Zipkin as a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures (Currency Conversion Service, Currency Exchange Service).

To enable this tracer, you need to have a working Zipkin server.

Run Zipkin server with Docker: 
```docker run -d -p 9411:9411 openzipkin/zipkin```
- Port: 9411
- UI: http://localhost:9411/zipkin/

## API Gateway
Using Spring Cloud Gateway for request routing.
- Port: 8765

```
GET http://localhost:8765/currency-conversion/from/USD/to/VND/quantity/10000
GET http://localhost:8765/currency-conversion-feign/from/USD/to/VND/quantity/10000
```
