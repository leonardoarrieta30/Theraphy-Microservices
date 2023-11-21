FROM openjdk:20-jdk-slim
COPY ./target/eureka-server-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch eureka-server-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/eureka-server-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /eureka-server-0.0.1-SNAPSHOT.jar