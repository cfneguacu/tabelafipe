FROM maven:3.8.7-eclipse-temurin-19-alpine as builder

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean verify

FROM eclipse-temurin:19-jre-alpine

COPY --from=builder target/*.jar tabelafipe.jar
EXPOSE 8080

CMD ["java","-jar","tabelafipe.jar"]

