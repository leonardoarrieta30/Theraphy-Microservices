FROM openjdk:20-jdk-slim
COPY ./target/physiotherapist-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch physiotherapist-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/physiotherapist-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /physiotherapist-service-0.0.1-SNAPSHOT.jar