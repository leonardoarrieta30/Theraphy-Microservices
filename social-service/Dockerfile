FROM openjdk:20-jdk-slim
COPY ./target/social-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch social-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/social-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /social-service-0.0.1-SNAPSHOT.jar