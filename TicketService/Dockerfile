FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target/TicketService-0.0.1-SNAPSHOT.jar /app/ticket-service.jar

EXPOSE 8082

CMD ["java", "-jar", "ticket-service.jar"]
