#
# Build stage
#
FROM maven:3.6.3-jdk-8-slim AS build
COPY pom.xml /usr/src/app
COPY src /usr/src/app/src
RUN mvn dependency:go-offline -B
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=build /usr/src/app/target/sputnikfy-0.0.1-SNAPSHOT.jar /usr/app/sputnikfy.jar
COPY /resources/actividad.xsd /resources/actividad.xsd
RUN chmod 777 /resources/actividad.xsd
RUN chmod 775 /usr/app
EXPOSE 8080
USER spring:spring
ENTRYPOINT ["java","-jar","/usr/app/sputnikfy.jar"]
