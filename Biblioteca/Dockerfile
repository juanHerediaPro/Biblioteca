# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder

WORKDIR /app

# Copia los archivos necesarios para la compilación (relativo a la ubicación del Dockerfile)
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn

# Compila el proyecto
RUN ./mvnw package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime)
# ====================================================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copia el JAR generado de la etapa de compilación
COPY --from=builder /app/target/*.jar app.jar

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]