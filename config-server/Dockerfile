# Usar la imagen oficial de OpenJDK 20
FROM openjdk:20-jdk-slim

# Crear el directorio donde residirá la aplicación
WORKDIR /app

# Añadir el archivo .jar al contenedor
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

# Ejecutar la aplicación
CMD ["java", "-jar", "config-server.jar"]
