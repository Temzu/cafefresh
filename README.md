# Cafefresh

## How to run?

### Build all modules:

`cafefresh$ mvn -DskipTests clean package`

### Build infrastructure modules in docker:

`cafefresh$ ./build-docker-image.sh`

### Start all microservices:

`cafefresh$ docker-compose up -d`

### When all microservices have been started, go to a browser and use this path:
  `http://localhost/cafefresh/index.html`

## Project Goals:
- Using the monolith architecture
- Сreate services: product, order, authentification, report, cart
- Storing JWT tokens and Carts in Redis
- Make simple frontend on AngularJS

## Features
- Spring boot 3.1
- Java 17
- HTML + JS + AngularJS
- Spring Web, Rest, Data
- Spring Security + JWT
- REST-assured 5 + JUnit 5
- Redis
- Flyway + H2
- Lombok
- Spring docker-compose module

## Screenshot of the architecrure
![image](https://github.com/Temzu/cafefresh/assets/51756264/4f6d35b0-99e3-45b9-9dd7-8128530ba6dd)

## Screenshot of the database architecrure
![image](https://user-images.githubusercontent.com/51756264/166445201-c90b3086-7274-410a-8e94-778fe85c6c59.png)

## Screenshot of the product page
![image](https://github.com/Temzu/market-microservices/assets/51756264/a56d0301-863f-42b1-b8e4-d2b85d35e2dc)

## Screenshot of the cart page
![image](https://github.com/Temzu/cafefresh/assets/51756264/a62ee9c0-15b2-42c9-9819-d89d08a0238e)




