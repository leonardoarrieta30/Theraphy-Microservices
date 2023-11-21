FROM openjdk:20-jdk-slim
COPY ./target/patient-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch patient-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/patient-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /patient-service-0.0.1-SNAPSHOT.jar