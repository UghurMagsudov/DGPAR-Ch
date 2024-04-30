FROM openjdk:22-jdk-slim as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
WORKDIR /app

COPY --from=build /app/target/demoLogin-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
