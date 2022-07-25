# ოεძἶʂƈɾεεղ
**The patients health keeper**

MediScreen help in detecting risk for disease. <br>
Using a predictive analysis of patient populations at an affordable cost.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development
and testing purposes.</br>
See deployment for notes on how to deploy the project on a live system.


### App built with
What things you need to install this App

- Java 17
- SpringBoot 2.7.1
- mySql 8.0.29
- mongoDB 6.0
- Jpa Repository
- Maven
- Thymeleaf
- JUnit 5
- Jacoco
- Log4j


### Installing
A step by step series of examples that tell you how to get a development env running:

1.Install Java:
https://docs.oracle.com/javase/11/docs/technotes/guides/install/install_overview.html

2.Install Maven:
https://maven.apache.org/install.html

3.Install SpringBoot:
https://spring.io/projects/spring-boot

4.Install mySql:
https://www.mysql.com/downloads/

4.Install mongoDB:
https://www.mongodb.com/docs/manual/installation/

After downloading and installing it, no specific setup required.


### Data Architecture
MediScreen main app project:
* Service Patient (Main)
* Service Notes
* Service Assessment
* Service mysqldb
* Service mongoDB

All Deployed in with Docker


### Build Application
Build all jar from main directory :
```
mvn clean install
```


### Docker Start Application
Launch command in terminal:
```
docker-compose up -d
```

Shutdown server app
```shell
Ctrl + C
```

Shutdown docker and clean up containers:
```
docker-compose down
```


### URL Access
TourGuide URL allow access to all url containers

Open your browser and get local url:
```html
https://0.0.0.0:8080/ or https://localhost:8080/
```


### Application Open Ports
- Notes: ```https://0.0.0.0:8081```
- Assessment: ```https://0.0.0.0:8082```


### Testing
The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.<br>
Run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

* Run unit tests, use command:

```shell
mvn:test
```

* Run integration tests, use command:

```shell
mvn failsafe:integration-test
```

* Run all tests, use command:

```shell
mvn verify
```


### Reports
Maven site to get all reports:

- **SureFire Report** for all unit Tests.
- **Jacoco Report** for tests coverage.
- **SpotBugs Report** for find bugs. 

Run build site, use command:

```shell
mvn site
```

Access file directory : `target/site` 
Run the `index.html` in your web browser.


### Jacoco Coverage
Jacoco coverage is automatically done with tests.

Access file directory : `target/site/jacoco/index.html`