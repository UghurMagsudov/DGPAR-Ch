# Use a base image with JDK 22 to match your Maven setup
FROM openjdk:22-jdk-slim as build

# Set the working directory in the Docker image
WORKDIR /app

# Copy the Maven pom.xml file and the source code
COPY pom.xml .
COPY src ./src

# Install Maven dependencies and package the application
RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests

# Use a new stage to discard the Maven build context and keep the container light
FROM openjdk:22-jdk-slim
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/demoLogin-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
