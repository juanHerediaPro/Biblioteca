# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn
RUN ./mvnw clean package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime)
# ====================================================
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# **LÍNEA CRÍTICA CORREGIDA:** Copia el JAR con su nombre completo
# Esto asegura que el archivo se encuentre, incluso si el <finalName> falla.
COPY --from=builder /app/target/Biblioteca-0.0.1-SNAPSHOT.jar /app.jar

# Define el puerto de Cloud Run (8080)
ENV PORT 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]