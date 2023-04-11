# elevenproof-api

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in
Dutch) (BSN Burger Service Nummer, Bank Account number, etc...)

## How the eleven proof works
| BSN number  | 0 | 5 | 6 | 7 | 0 | 4 | 0 | 3 | 0 | 5 |

|             | #1  | #2  | #3  | #4  | #5  | #6  | #7  | #8  | #9  |        |     |        |   |
|-------------|-----|-----|-----|-----|-----|-----|-----|-----|-----|--------|-----|--------|---|
| Number      | 2   | 5   | 3   | 0   | 4   | 7   | 1   | 4   | 6   |        |     |        |   |
|             | x   | x   | x   | x   | x   | x   | x   | x   | x   |        |     |        |   |
| Multipliers | 9   | 8   | 7   | 6   | 5   | 4   | 3   | 2   | -1  |        |     |        |   |
| result      | 18  | 40  | 21  | 0   | 20  | 28  | 3   | 8   | -6  | Total  | 132 | % 11 = | 0 |

#### Application information

Name: elevenproof-api
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Requirements

This project uses:

```
Java 17
Maven
```

#### Use sonarqube
Create environment variables containing url/login/password
``` 
SONAR_URL=http://sonar.host.com
SONAR_LOGIN=login
SONAR_PASSWORD=password
```
After building the project run:
```
$ mvn sonar:sonar
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
