# elevenproof-api

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in NL:
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
mvn clean install
```

#### Application usage

Generate 1 BSN or BankAccount:

```
java -jar elevenproof-api-1.0-SNAPSHOT.jar generate <bsn|bank>
```

Validate 1 BSN or BankAccount:

```
java -jar elevenproof-api-1.0-SNAPSHOT.jar validate <bsn|bank> <nummer>
```
