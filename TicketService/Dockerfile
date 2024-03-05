# First stage: Build the project
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix ./mvnw

RUN ./mvnw dependency:resolve

COPY src src

RUN ./mvnw clean package -DskipTests=true

# Second stage: Create the final image
FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY --from=builder /app/target/TicketService-0.0.1-SNAPSHOT.jar ./ticket-service.jar

EXPOSE 8082

# Command to run the application
CMD ["java", "-jar", "ticket-service.jar"]

