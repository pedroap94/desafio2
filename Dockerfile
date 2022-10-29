FROM openjdk:17-jdk-slim

ADD target/*.jar app.jar

VOLUME /tmp

ENV TZ America/Sao_Paulo

CMD exec java -jar app.jar