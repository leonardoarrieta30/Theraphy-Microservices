# Usar la imagen oficial de OpenJDK 20
FROM openjdk:20-jdk-slim

# Crear el directorio donde residirá la aplicación
WORKDIR /app

# Añadir el archivo .jar al contenedor
COPY target/social-service-0.0.1-SNAPSHOT.jar /app/social-service.jar

# Ejecutar la aplicación
CMD ["java", "-jar", "/social-service.jar"]