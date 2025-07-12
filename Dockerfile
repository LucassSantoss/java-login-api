# Estágio 1: Build da aplicação com JDK 24
FROM eclipse-temurin:24-jdk AS build

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml para o contêiner
COPY pom.xml .

# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila a aplicação e gera o .jar
RUN mvn package -DskipTests

# Estágio 2: Criação da imagem de produção com JRE 24
FROM eclipse-temurin:24-jre

# Define o diretório de trabalho
WORKDIR /app

# Copia o .jar do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]