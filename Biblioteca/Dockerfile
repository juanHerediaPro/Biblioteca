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
# Etapa 2: EJECUCIÓN (Runtime)
# ====================================================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# NOTA: Cambiamos 'app.jar' a '*SNAPSHOT.jar'
# Esto asume que el nombre de tu JAR terminado es 'Biblioteca-X.X.X-SNAPSHOT.jar'
COPY --from=builder /app/target/*.jar app.jar

# Ejecuta la aplicación. Añadimos el contexto de servidor explícito.
# La bandera server.address=0.0.0.0 es crucial para contenedores
ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]