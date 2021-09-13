FROM java:8-jdk-alpine
COPY ./target/configuration-sidecar-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "configuration-sidecar-0.0.1-SNAPSHOT.jar"]