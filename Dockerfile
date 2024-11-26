FROM openjdk:17-jdk-alpine
COPY ./target/pokemon-application.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pokemon-application.jar"]