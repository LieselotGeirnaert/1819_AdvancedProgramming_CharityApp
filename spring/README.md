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

### API Webserver

http://10.129.32.15:8080

## Available Rest Calls

### Charity

#### Get requests

#####http://10.129.32.15:8080/charity
returns all charities

[
  {
    "id": 1,
    "name": "test charity",
    "description": "test charity for the test",
    "linkToLogo": ""
  },
  {
    "id": 2,
    "name": "test charity 2",
    "description": "test charity for the test nr2",
    "linkToLogo": ""
  }

######http://10.129.32.15:8080/charity/{id}
returns charity with specific id

{
  "id": 1,
  "name": "test charity",
  "description": "test chairity for the test",
  "linkToLogo": ""
}

#### Post requests

######http://10.129.32.15:8080/chairity
create a charity 

sample post request input:
{
    "name": "oxfam",
    "description": "arme mensen",
    "linkToLogo": ""
}