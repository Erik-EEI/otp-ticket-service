# Module Documentation - Partner Module
This document provides an overview of the libraries used and the logic implemented in the Partner Module.

### Libraries Used

> #### Core Framework
- **Spring Boot (v3.2.3)**: A comprehensive framework for building production-ready Spring-based applications with minimal configuration.
- **Spring Boot Starter Data JPA**: Simplifies data access and persistence using the Java Persistence API (JPA).
- **Spring Boot Starter Web**: Essential components for developing web applications with Spring MVC.
- **Spring Boot DevTools**: Development-time tools to enhance productivity and streamline development workflows.

> #### Database Connectivity
- **H2 Database**: In-memory database used for development and testing purposes.
- **Project Lombok**: Reduces boilerplate code in Java classes with annotations.

> #### Security
- **Spring Boot Starter Security**: Provides security features for the application.
- **dotenv-java (v3.0.0)**: Loads environment variables from a `.env` file into the application. This can be crucial for managing sensitive information like database credentials and API keys.

> #### Logging and Documentation
- **Logback Classic**: A flexible and customizable logging framework for the application.
- **Springdoc OpenAPI Starter Web MVC UI**: Generates interactive API documentation using OpenAPI specifications.

> #### Testing
- **JUnit**: A popular testing framework for Java applications.


### Application Logic

- **Model Layer**: Defines JPA entities representing events and reservations.
- **Repository Layer**: Utilizes Spring Data JPA repositories for data access operations related events and reservations.
- **Service Layer**: Contains business logic for managing partner-related functionalities such as event retrieval and reservation handling operations.
- **Controller Layer**: Defines RESTful API endpoints for handling partner-related operations.
- **Security Configuration**: Configures Spring Security to secure endpoints and authenticate ticket module requests with api key and secret.

### Database
> The H2 database is an easy-to-use, embedded relational database, which is a great choice for storing static data in a Spring Boot application.<br>
> It is easy to use and configure, ideal for rapid development.There's no need for setting up an external environment, and the database can be easily reset during development.

### Use cases

- Get a list of all events
- Get a specific event ( detailed or simple format )
- Reserve a seat
- Get a specific reservation
- Cancel a reservation
  
![UML - UseCase - Partner module.png](uml%2FUseCase%2FUML%20-%20UseCase%20-%20Partner%20module.png)