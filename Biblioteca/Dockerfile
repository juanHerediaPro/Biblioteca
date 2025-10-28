# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder

WORKDIR /app

# Copia los archivos necesarios para la compilación
COPY pom.xml .
COPY src ./src
COPY mvnw .
RUN chmod +x mvnw
COPY .mvn ./.mvn

# Limpia y compila el proyecto (CRÍTICO: Añadir 'clean')
RUN ./mvnw clean package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime) - CRÍTICA
# ====================================================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia el JAR generado con su nombre exacto
# Reemplaza 'Biblioteca-0.0.1-SNAPSHOT.jar' con el nombre que está en tu target/
# En la Etapa 2 de tu Dockerfile, usa el nombre 'app.jar'
COPY --from=builder /app/target/app.jar app.jar

# Define el puerto de Cloud Run (8080)
ENV PORT 8080

# Ejecuta la aplicación de forma robusta
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]