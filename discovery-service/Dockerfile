FROM java:8-jdk-alpine
COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]