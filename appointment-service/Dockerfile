FROM openjdk:20-jdk-slim
COPY ./target/appointment-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch appointment-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/appointment-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /appointment-service-0.0.1-SNAPSHOT.jar