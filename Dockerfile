# Etapa 1: build
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

# Atualiza pacotes do sistema antes de buildar (mesmo que seja só Java, ajuda)
RUN apt-get update && apt-get upgrade -y && \
    ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Atualiza o sistema no runtime também
RUN apt-get update && apt-get upgrade -y

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
