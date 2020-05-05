#
# Build stage
#
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

#
# Package stage
#
FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/sputnikfy-0.0.1-SNAPSHOT.jar /usr/app/sputnikfy.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/sputnikfy.jar"]