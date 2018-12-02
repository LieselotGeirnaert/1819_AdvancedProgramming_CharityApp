# Spring back-end

## Why you should use maven wrapper

- Everyone uses the same maven version
- Maven does not have to be installed on your machine

Source: http://bennet-schulz.com/2018/04/12/quicktip-use-a-maven-wrapper/

## Contributing

Copy src/main/resources/application-prod.properties to src/main/resources/application-dev.properties and change to your local environment.

### Building the project

Unix: `./mvnw clean install`  
Windows: `.\mvnw.cmd clean install`

### Running Spring boot

Unix: `./mvnw spring-boot:run`  
Windows: `.\mvnw.cmd spring-boot:run`