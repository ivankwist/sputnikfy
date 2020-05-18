#
# Build stage
#
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/sputnikfy-0.0.1-SNAPSHOT.jar /usr/app/sputnikfy.jar
COPY /resources/actividad.xsd /resources/actividad.xsd
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/sputnikfy.jar"]
