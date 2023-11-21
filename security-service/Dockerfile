FROM openjdk:20-jdk-slim
COPY ./target/security-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch security-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/security-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /security-service-0.0.1-SNAPSHOT.jar