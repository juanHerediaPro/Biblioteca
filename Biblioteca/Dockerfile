# ====================================================
# Etapa 1: COMPILACIÓN (Builder)
# ====================================================
# Usa una imagen de Maven con Java 17 para compilar
FROM maven:3.9.4-eclipse-temurin-17-focal AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven primero para aprovechar el caché
COPY pom.xml .

# Copia el código fuente
COPY src ./src

# Copia los wrappers de Maven
COPY mvnw .
COPY .mvn ./.mvn

# Ejecuta la limpieza, descarga dependencias y compila el JAR (CRÍTICO: esto crea el app.jar)
# Usamos 'package' para generar el ejecutable.
RUN ./mvnw clean package -DskipTests

# ====================================================
# Etapa 2: EJECUCIÓN (Runtime) - CRÍTICA
# ====================================================
# Usa una imagen base con solo JRE, más pequeña y segura
FROM eclipse-temurin:17-jre-focal

# Establece el directorio de trabajo
WORKDIR /app

# Argumento para el nombre del JAR (definido como 'app.jar' en tu pom.xml)
# Si tu pom.xml usa <finalName>app</finalName>, el archivo final es app.jar.
ARG JAR_FILE=target/app.jar

# Copia el JAR generado desde la etapa 'builder' y lo pone en el directorio /app
# La sintaxis: COPY --from=etapa /ruta/origen /ruta/destino
COPY --from=builder /app/${JAR_FILE} /app.jar

# Define el puerto de Cloud Run (8080)
ENV PORT 8080

# Ejecuta la aplicación. El /app.jar ahora debe existir.
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]