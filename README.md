# social-media-application
A small social media application focusing on Java Collections

### Getting Started
> mvn clean package

> java -jar target/social-media-application-1.0-SNAPSHOT.jar

### Architectural Viewpoints
* @JsonGetter : To avoid exposure of list of followers and followings. To avoid accidental edit/delete
* Equals & Hashcode IDs kept same to get correct result
* Integer being Immutable kept as Id in equals and hashcode
* List of Followers & Followees can be simultaneously edited, So either could use synchronized block, but that maintains total data block. Instead to achieve line level lock, Concurrent Hash Map's concept is used. Concurrent Hash Set using the ID as keys while boolean as value in new KeySetFromMap or proper values in newKeySet
* Parameterized Constructor used based on input values 
* Default constructor private : Only to use for JSON

### API Endpoint Contracts
|HTTP Method|Endpoint URL|
|--|--|
|GET|http://localhost:8081/users|
|POST|http://localhost:8081/users/posts|
|GET|http://localhost:8081/users/3/posts|
|GET|http://localhost:8081/users/4/follow/2|
|GET|http://localhost:8081/users/4/unfollow/2|
|GET|http://localhost:8081/actuator/health|

Please refer entire [POSTMAN Collection JSON](https://github.com/anupama-sinha/social-media-application/blob/main/Social%20Media%20Application.postman_collection.json) attached with Project

Alternatively, please use [Swagger for Social Media Application](http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

### Future Scope(To be done)
* Test Coverage Reports using Jacoco Plugin
* Integration of Logging Framework
* Exception Handling for all scenarios
