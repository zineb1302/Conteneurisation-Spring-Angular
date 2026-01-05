# Stage 1: Build avec Maven
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package -DskipTests

# Stage 2: Image finale legere
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=builder /app/target/*.jar client-service.jar
ENTRYPOINT ["java","-jar","/client-service.jar"]
