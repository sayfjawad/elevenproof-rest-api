# elevenproof-api

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in 
Dutch) (BSN Burger Service Nummer, Bank Account number, etc...)

#### Application information

Name: elevenproof-api
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Requirements

This project uses:

```
Java 17
Maven
```

#### Build Application

```
mvn clean package
```

#### Application usage
Generate BSN or BankAccount:

```
java -jar elevenproof-api-1.0-SNAPSHOT.jar generate <bsn|bank>
```

Validate BSN or BankAccount:

```
java -jar elevenproof-api-1.0-SNAPSHOT.jar validate <bsn|bank> <nummer>
```
