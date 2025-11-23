# Dockerfile-api-gateway
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /api-gateway
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /api-gateway
COPY --from=build /api-gateway/target/*.jar api-gateway.jar
EXPOSE 8761
ENTRYPOINT [ "java","-jar","api-gateway.jar" ]