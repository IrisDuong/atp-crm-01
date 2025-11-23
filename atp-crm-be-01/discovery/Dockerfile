# Dockerfile-discovery
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /discovery
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /discovery
COPY --from=build /discovery/target/*.jar discovery.jar
EXPOSE 8761
ENTRYPOINT [ "java","-jar","discovery.jar" ]