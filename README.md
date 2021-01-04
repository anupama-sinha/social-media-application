# social-media-application
A small social media application focusing on Java Collections

### Getting Started
> mvn clean package

> java -jar target/social-media-application-1.0-SNAPSHOT.jar

### API Endpoint Contracts
|HTTP Method|Endpoint URL|
|--|--|
|GET|http://localhost:8081/users|
|POST|http://localhost:8081/users/posts|
|GET|http://localhost:8081/users/3/posts|
|GET|http://localhost:8081/users/4/follow/2|
|GET|http://localhost:8081/users/4/unfollow/2|

Please refer entire [POSTMAN Collection JSON](https://github.com/anupama-sinha/social-media-application/blob/main/Social%20Media%20Application.postman_collection.json) attached with Project

### Future Scope(If required)
* Implementation of Exception Handling
* Unit & Integration Testing with Jacoco Reports
* OpenAPI3 Swagger & Actuator Implementation
