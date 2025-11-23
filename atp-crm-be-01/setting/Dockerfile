# Dockerfile-setting
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /setting
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /setting
COPY --from=build /setting/target/*.jar setting.jar
EXPOSE 8761
ENTRYPOINT [ "java","-jar","setting.jar" ]