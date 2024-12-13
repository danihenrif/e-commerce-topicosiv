# Etapa 1: Construção do projeto com Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o pom.xml e o diretório src para o container
COPY store/pom.xml .
COPY store/src ./src

# Rodando o Maven para compilar o projeto e gerar o arquivo JAR
RUN mvn clean install -DskipTests

# Etapa 2: Imagem para rodar a aplicação
FROM openjdk:17-slim

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o JAR gerado pela etapa anterior
COPY --from=build /app/target/*.jar /app/app.jar

# Expondo a porta onde o aplicativo Spring Boot vai rodar
EXPOSE 8081

# Comando para rodar o JAR com Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
