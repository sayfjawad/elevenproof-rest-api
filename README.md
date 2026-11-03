# elevenproof-api

Opensource Java API for validating and generating numbers that use the ElevenProof (elfproef in
Dutch) (BSN Burger Service Nummer, Bank Account number, etc...)

## How the eleven proof works

This example uses the Dutch implementation of the eleven proof for the social security number
equivalent 'BSN - Burger Service Nummer'.

| BSN number | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |

|                     | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 |       |     |        |   |
|---------------------|----|----|----|----|----|----|----|----|----|-------|-----|--------|---|
| BSN Digits          | 2  | 5  | 3  | 0  | 4  | 7  | 1  | 4  | 6  |       |     |        |   |
|                     | x  | x  | x  | x  | x  | x  | x  | x  | x  |       |     |        |   |
| BSN Multipliers     | 9  | 8  | 7  | 6  | 5  | 4  | 3  | 2  | -1 |       |     |        |   |
| Eleven proof result | 18 | 40 | 21 | 0  | 20 | 28 | 3  | 8  | -6 | Total | 132 | % 11 = | 0 |

#### Application information

Name: elevenproof-api
Maintainer: Sayf jawad ([sayf@multicode.nl](mailto:sayf@multicode.nl))

#### Architecture

This repository is the Spring Boot REST API only. The eleven-proof generation and validation
logic itself lives in a separate library,
[eleven-proof-lib](https://github.com/sayfjawad/eleven-proof-lib), published to Maven Central
as `nl.multicode.elevenproof:services`. This `application` module just wires that library's
services up to HTTP controllers and a Swagger UI.

Production runs on the `elevenproof` namespace of the `k3s-multicode` cluster (pod on node
`c4130`, no in-cluster Service/Ingress — reached via `hostPort`), fronted by an nginx reverse
proxy + Let's Encrypt certificate on the edge VPS, reachable at
[https://eleven.multicode.nl](https://eleven.multicode.nl). The desired state (Deployment,
image-automation) lives in
[multicode-infra](https://github.com/sayfjawad/multicode-infra/tree/main/k8s/namespaces/elevenproof),
applied by Flux (GitOps) — not from this repo's CI.

#### Requirements

This project uses:

```
Java 21
Spring Boot 3.x
Gradle
docker
kubernetes (k8s) # P.S. docker-desktop has a kubernetes implementation you can use
helm
kubectl
```

#### Build Application

```
./mvn clean install
```

#### CI/CD: build, push and deploy (GitOps)

On every push to `main`, `.github/workflows/java-ci.yml` builds and tests the project, then builds
a Docker image with [Jib](https://github.com/GoogleContainerTools/jib) (no Dockerfile needed) and
pushes it to GHCR tagged both `<git-sha>` and `main-<run-number>`. That's where this repo's CI
stops — it never touches the cluster.

From there it's pull-based GitOps, entirely in
[multicode-infra](https://github.com/sayfjawad/multicode-infra):
- Flux's `image-reflector-controller` watches GHCR for new `main-<N>` tags.
- `image-automation-controller` commits the new tag into
  `k8s/namespaces/elevenproof/elevenproof-rest-api.yaml` in that repo.
- Flux's normal reconciliation (already running in-cluster) applies it.

No `KUBECONFIG` secret, no cluster credentials of any kind in this repo's CI — that was the whole
point of moving to GitOps (a GitHub-hosted runner can't reach the cluster's private network
anyway).

#### Integration testing using kubernetes
Running automated tests to ensure functional expectations are met and prevent regression

## requisites
Install Docker-Desktop and enable kuberenetes support in settings

## run the integration tests
Make sure docker-desktop is running, build the necessary docker image using 'jib'
``` 
$ cd application
$ ./mvn jib
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

$ mvn clean install

```
run the application 
```

$ java -jar application/target/application-1.0-SNAPSHOT.jar

```
test by browser
```

http://localhost:8080/api/swagger-ui/index.html

```

In production, the same Swagger UI is available at
https://eleven.multicode.nl/api/swagger-ui/index.html.
test by command line
```

# BSN

$ curl -X 'GET' 'http://localhost:8080/api/bsn/generate' -H 'accept: */*'
$ curl -X 'GET' 'http://localhost:8080/api/bsn/validate/052863785' -H 'accept: */*'

#BANK
$ curl -X 'GET' 'http://localhost:8080/api/bank/generate' -H 'accept: */*'
$ curl -X 'GET' 'http://localhost:8080/api/bank/validate/0810660385' -H 'accept: */*'

```

