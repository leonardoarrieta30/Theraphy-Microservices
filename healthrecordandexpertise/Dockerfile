FROM openjdk:20-jdk-slim
COPY ./target/healthrecordandexpertise-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch healthrecordandexpertise-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/healthrecordandexpertise-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /healthrecordandexpertise-0.0.1-SNAPSHOT.jar