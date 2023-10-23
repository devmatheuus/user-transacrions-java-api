FROM maven:3.9.2-eclipse-temurin-20-alpine AS build
COPY . .

RUN mvn clean package -DskipTests
FROM openjdk:20
COPY --from=build /target/user-transactions-api-0.0.1.jar user-transactions-api.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","user-transactions-api.jar"]