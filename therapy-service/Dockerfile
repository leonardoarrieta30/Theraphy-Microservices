FROM openjdk:20-jdk-slim
COPY ./target/therapy-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch therapy-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/therapy-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /therapy-service-0.0.1-SNAPSHOT.jar