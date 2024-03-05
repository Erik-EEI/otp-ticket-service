# Module Documentation - Ticket Service
This document provides an overview of the libraries used and the logic implemented in the Ticket Service Module and the Partner Module.
>**IMPORTANT NOTICE:** For testing purposes, the timestamp validation logic of the Ticket module is turned off, because all of the provided events are in the past, hence only that custom exception is thrown for the reservation requests!<br> **CONFIGURATION:** You can turn the timestamp validation ON / OFF in the `application.properties` file by setting the `api.timestamp.validation=` field to either *true* or *false*!
### Libraries Used
> #### Core Framework
- **Spring Boot (v3.2.3)**: A comprehensive framework for building production-ready Spring-based applications with minimal configuration.
- **Spring Boot Starter Data JPA**: Simplifies data access and persistence using the Java Persistence API (JPA).
- **Spring Boot Starter Web**: Essential components for developing web applications with Spring MVC.
- **Spring Boot DevTools**: Development-time tools to enhance productivity and streamline development workflows.

> #### Database Connectivity
- **PostgreSQL Driver**: Enables connectivity to PostgreSQL databases.
- **Project Lombok**: Reduces boilerplate code in Java classes with annotations.
- **Spring Boot Starter Cache (v3.1.5)**: Facilitates caching support within the application.

> #### Testing
- **JUnit**: A popular testing framework for Java applications.
- **JaCoCo Maven Plugin (v0.8.11)**: Generates code coverage reports for the application.

> #### Security
- **dotenv-java (v3.0.0)**: Loads environment variables from a `.env` file into the application. This can be crucial for managing sensitive information like database credentials and API keys.

> #### Logging and Documentation
- **Logback Classic (v1.4.14)**: A flexible and customizable logging framework for the application.
- **Springdoc OpenAPI Starter Web MVC UI (v2.3.0)**: Generates interactive API documentation using OpenAPI specifications.

### Application Layers

- **Model Layer**: Defines JPA entities representing various data entities in the ticketing system.
- **Repository Layer**: Utilizes Spring Data JPA repositories to interact with the database and perform CRUD operations on entities.
- **Service Layer**: Contains business logic for managing reservations, users, and other functionalities related to the ticket service.
- **Controller Layer**: Defines RESTful API endpoints for handling operations with client..
- **Security Configuration**: Provided by custom user-token authentication method from the CORE module.

### Database
> Considering its reliability, advanced features, and compatibility, ***PostgreSQL*** is a suitable choice for powering the ticket service module that demand scalability, and flexibility in data management.

### Use cases

- Get a list of all events
- Get a specific event
- Reserve and pay for a seat<br>

![UML UseCase Ticket Service](../uml/UseCase/UML%20-%20UseCase%20-%20Ticket%20Service.png)

