# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder

WORKDIR /app

# Copia los archivos necesarios para la compilación
COPY pom.xml .
COPY src ./src
COPY mvnw .
# *** LÍNEA CORREGIDA AÑADIDA AQUÍ ***
RUN chmod +x mvnw
# ***********************************
COPY .mvn ./.mvn

# Compila el proyecto
RUN ./mvnw package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime) - CRÍTICA
# ====================================================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Define el puerto de Cloud Run (8080)
ENV PORT 8080

# Ejecuta la aplicación de forma robusta
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]