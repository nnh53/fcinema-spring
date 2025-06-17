# === Build Stage ===
FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# === Runtime Stage ===
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/fcinema-spring-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
