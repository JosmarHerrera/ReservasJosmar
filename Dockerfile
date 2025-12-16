# Etapa 1: Construir
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN ls -R .   # esto lista todo lo que Docker ve
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar SOLO el jar generado
COPY --from=build /app/target/*.jar app.jar
COPY pom.xml .
# Cloud Run usa PORT, Spring Boot debe escucharlo
ENV PORT=8080
EXPOSE 8080

# Spring Boot por default escucha en 8080,
# pero Cloud Run puede usar otro, así que se lo forzamos:
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]