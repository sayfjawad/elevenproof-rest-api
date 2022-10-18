# elevenproof-api

Java Eleven-Proof API (BSN Burger Service Nummer, SocialService-number, Bank Account, etc...)

#### Application information

Name: elevenproof-api
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Requirements

This project makes use of:

```
Java 17
Maven
```

#### Build Application
```
mvn clean install
```

#### Application usage
Generate 1 BSN:

```
java -jar bsn-generator-1.0-SNAPSHOT.jar 
```

Validate 1 BSN:

```
java -jar bsn-generator-1.0-SNAPSHOT.jar validate <nummer>
```
