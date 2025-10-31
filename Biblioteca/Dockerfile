# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
# Usa una imagen de Maven con Java 17 para compilar
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven, el código fuente y los wrappers
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn

# Asegura que el wrapper de Maven sea ejecutable (Corrección 1)
RUN chmod +x mvnw

# Ejecuta la limpieza, descarga dependencias y compila el JAR
# La configuración 'package' generará el ejecutable, incluso con el error de JPA en el pom.xml
RUN ./mvnw clean package -DskipTests

# **PASO DE DIAGNÓSTICO:** Muestra el contenido del directorio 'target'
# y falla si el JAR esperado no existe. Esto nos dará un error explícito en Cloud Build
# si el JAR no se creó correctamente (Corrección 2)
RUN ls -l target/
RUN test -f target/Biblioteca-0.0.1-SNAPSHOT.jar || (echo "ERROR CRÍTICO: El JAR no se encontró en 'target/'. Revisar logs de Maven." && exit 1)

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime)
# ====================================================
# Usa una imagen base con solo JRE para un contenedor más pequeño y rápido
FROM eclipse-temurin:17-jre-focal

# Establece el directorio de trabajo
WORKDIR /app

# **LÍNEA CRÍTICA CORREGIDA:** Copia el JAR con el nombre por defecto (project-version.jar)
# y lo renombra como /app.jar (Corrección 3 - Final)
COPY --from=builder /app/target/Biblioteca-0.0.1-SNAPSHOT.jar /app.jar

# Define el puerto de Cloud Run (8080)
ENV PORT 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]