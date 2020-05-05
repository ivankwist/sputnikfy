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
FROM openjdk:8
COPY --from=build /usr/src/app/target/sputnikfy-0.0.1-SNAPSHOT.jar /usr/app/sputnikfy.jar
RUN mkdir /usr/app/resources
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/sputnikfy.jar"]