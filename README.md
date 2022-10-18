# bsn-generator

Java BSN Generator (Burger Service Nummer, Sofinummer)

#### Applicatie informatie

Naam: bsn-generator
Maintainer: Sayf jawad ([sayf4all@hotmail.com](mailto:sayf4all@hotmail.com))

#### Technieken

Dit project maakt gebruik van:

```
Java 17
Maven
```

#### Applicatie lokaal starten

Bouwen tegen JDK 17

```
mvn clean install
```

De applicatie kan lokaal gestart worden met:

Genereer 1 BSN door uit te voeren:

```
java -jar bsn-generator-1.0-SNAPSHOT.jar 
```

Valideer 1 BSN door uit te voeren:

```
java -jar bsn-generator-1.0-SNAPSHOT.jar validate <nummer>
```

Of door gebruik te maken van een IDE met Spring integratie (zoals IntelliJ).
