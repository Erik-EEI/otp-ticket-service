FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target/Partner-0.0.1-SNAPSHOT.jar /app/partner-module.jar

EXPOSE 8081

CMD ["java", "-jar", "partner-module.jar"]
