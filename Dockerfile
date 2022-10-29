# Build stage
FROM maven:3.8.6-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:18-ea-20-jdk-slim
COPY --from=build /home/app/target/account-manager-0.0.1-SNAPSHOT.jar /usr/local/lib/account-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/account-manager.jar"]