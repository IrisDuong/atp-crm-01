# Dockerfile-central-config
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /central-config
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /central-config
COPY --from=build /central-config/target/*.jar central-config.jar
EXPOSE 8761
ENTRYPOINT [ "java","-jar","central-config.jar" ]