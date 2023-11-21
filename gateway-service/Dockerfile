FROM openjdk:20-jdk-slim
COPY ./target/gateway-service-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch gateway-service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/gateway-service-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /gateway-service-0.0.1-SNAPSHOT.jar