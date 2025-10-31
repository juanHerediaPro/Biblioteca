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

# Limpia y  compila el proyecto (CRÍTICO: Añadir 'clean')
RUN ./mvnw clean package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime) - CORREGIDA
# ====================================================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Corregido: Reemplaza el nombre genérico 'app.jar' por el nombre REAL del JAR que genera tu proyecto (Biblioteca-0.0.1-SNAPSHOT.jar)
# Y renómbralo a 'app.jar' para el ENTRYPOINT.
# (Asegúrate que el nombre 'Biblioteca-0.0.1-SNAPSHOT.jar' es el que Maven crea en tu caso)
COPY --from=builder /app/target/Biblioteca-0.0.1-SNAPSHOT.jar app.jar


# Ejecuta la aplicación de forma robusta
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]