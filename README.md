# User service

This is a service to work with users. It provides the functionality of
adding, updating, deleting and getting users(by id and all) with
incoming data validation.

H2 database was used in this service, for ease of start-up. Also
Liquebase was used for creating DB tables and injecting test users.
(http://localhost:8080/h2-console; username=sa; no password)

Also, Swagger was connected to this service to describe the available endpoints 
(you can see the UI version at the endpoint http://localhost:8080/swagger-ui).

### Technologies:

- Spring Boot (2.7.9)
- Spring Data
- Hibernate
- H2 DB
- Liquibase
- Swagger
- Lombok
- Mapstruct

