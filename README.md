# elevenproof-api

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in
Dutch) (BSN Burger Service Nummer, Bank Account number, etc...)

## How the eleven proof works

This example uses the Dutch implementation of the eleven proof for the social security number
equivalent 'BSN - Burger Service Nummer'.

| BSN number | 0 | 5 | 6 | 7 | 0 | 4 | 0 | 3 | 0 | 5 |

|             | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 |       |     |        |   |
|-------------|----|----|----|----|----|----|----|----|----|-------|-----|--------|---|
| Number      | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |       |     |        |   |
|             | x  | x  | x  | x  | x  | x  | x  | x  | x  |       |     |        |   |
| Multipliers | 9  | 8  | 7  | 6  | 5  | 4  | 3  | 2  | -1 |       |     |        |   |
| result      | 18 | 40 | 21 | 0  | 20 | 28 | 3  | 8  | -6 | Total | 132 | % 11 = | 0 |

#### Application information

Name: elevenproof-api
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Requirements

This project uses:

```
Java 17
Spring Boot 3.x
Maven
docker
kubernetes (k8s) # P.S. docker-desktop has a kubernetes implementation you can use
helm
kubectl
```

#### Build Application

```
mvn clean package
```

#### Use dockerhub.com

Create environment variables containing login/password to be able to register your container
images to dockerhub.com

``` 
DOCKER_HUB_USER={your dockerhub username}
DOCKER_HUB_PASS={your dockerhub password}
``` 

#### Use sonarqube
Create environment variables containing url/login/password to be able to connect your project to a
SonarQube/SonarCloud instance. 
``` 
SONAR_URL=http://sonar.host.com
SONAR_LOGIN=login
SONAR_PASSWORD=password
``` 


After building the project run:
```
$ mvn sonar:sonar
```


#### Integration testing using kubernetes
Running automated tests to ensure functional expectations are met and prevent regression

## requisites
Install Docker-Desktop and enable kuberenetes support in settings

## run the integration tests
Make sure docker-desktop is running, build the necessary docker image using 'jib'
``` 
$ cd application
$ mvn jib:dockerBuild
```
go to the integration-test folder
```

$ cd integration-test

```
run the integration test.sh script with install parameter then again with port parameter
```

$ cd integration-test
$ ./test.sh install
$ ./test.sh port

```
your kuberenetes pod should be running. 
Now you can run cucumber tests!

``` 
$ mvn clean install -Pintegration-test
```

#### Application usage

Generate BSN or BankAccount:
buid the application 
``` 

$ mvn clean package

```
run the application 
```

$ java -jar application/target/application-1.0-SNAPSHOT.jar

```
test by browser
```

http://localhost:8080/api/swagger-ui/index.html

```
test by command line
```

# BSN

$ curl -X 'GET' 'http://localhost:8080/api/bsn/generate' -H 'accept: */*'
$ curl -X 'GET' 'http://localhost:8080/api/bsn/validate/052863785' -H 'accept: */*'

#BANK
$ curl -X 'GET' 'http://localhost:8080/api/bank/generate' -H 'accept: */*'
$ curl -X 'GET' 'http://localhost:8080/api/bank/validate/0810660385' -H 'accept: */*'

```

