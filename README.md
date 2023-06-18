# miniERP_SpringBoot_Beginer
This project is built to serve the subject Java Technology - IE303.N23.CNCL.
Used a combination of MVC pattern and three-layer architecture.
Not restfull API.

CRUD for a salary management and accompanying modules using Spring Boot, Security, Thymeleaf, Bootstrap, Mysql, JPA and Hibernate.
## Requirements
1. Spring boot.
2. Java sdk 17.
3. MySQL.
## Time Format!!!
*Both day and month must have 2 numbers dd/mm/yyyy*

For example: 01/06/2023 
## Setup
**1. Clone the application**
```bash
git clone https://github.com/quankunshi/miniERP_SpringBoot_Beginer
```
**2. Create  database**
```bash
create database erpsystem
```
**3. Change mysql username and password**
+ open src/main/resources/application.properties
+ change spring.datasource.username and spring.datasource.password as per your mysql installation

**4. Run the app using maven**
```bash
mvn spring-boot:run
```
The app will start running at http://localhost:8080/admin