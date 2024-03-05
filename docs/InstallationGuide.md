# Installation Guide

Follow these steps to install and set up the Ticket Service Application on your system.

## Prerequisites

Before you begin, ensure you have the following prerequisites installed on your system:
```
- Java Development Kit (JDK) 17 or higher
- Maven
- Git
- (Docker)
```
## Clone the Repository

First, clone the otp-ticket-service repository to your local machine:

```bash
git clone https://github.com/Erik-EEI/otp-ticket-service
```
## Option 1 : Run via Docker Compose
Run a docker command to spin up both modules and a postgres database for the Ticket Service module.<br>
The environmental variables, connection details and DB Seed is pre-configured.
```bash
docker-compose up --build
```
## Option 2 : Build the Project

Navigate to the project directory and build both modules using Maven:

```bash
cd Partner/
mvn clean install

cd ..

cd TicketService/
mvn clean install
```

## Before running the application
Create and fill out a .env file in the `Ticket Service` application so it can connect to your own Postgres database.<br>

**.env file properties:**
- `DATABASE_URL=` - The connection URL to the Postgres DB.
- `DATABASE_USERNAME=` - The username to the DB.
- `DATABASE_PASSWORD=` - The password to the DB.
- `API_BASE_URL=` - The URL of the partner module

## Run the Application

Once the build is successful, and the .env file is created, you can run the application using Maven:

```bash
mvn spring-boot:run
```
After running both modules: <br>
- The Partner Module will start, and you can access it at `http://localhost:8081`.
- The Ticket Service Module will start, and you can access it at `http://localhost:8082`.

## Configuration

You can configure the modules by modifying the `application.properties` file located in the `src/main/resources` directory.

## Database Configuration
> **Important**: Both modules are configured to seed their own database on each run!
- The ***partner*** module uses an **H2 in-memory database** by default.
- The ***Ticket Service*** module uses an **Postgres database** by default.

## Logging Configuration

Both modules create external logs in a specified folder in their root directory.<br>
>You can find the module log files at: `/{{MODULE}}/logs`<br>

Logging configuration can be customized in the `logback.xml` file located in the `src/main/resources` directory.
