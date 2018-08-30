FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD addmService-0.1.0-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]