FROM java:8-jdk-alpine
COPY ./target/configuration-consumer-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "configuration-consumer-0.0.1-SNAPSHOT.jar"]