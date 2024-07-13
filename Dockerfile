FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
ADD target/task-0.0.1-SNAPSHOT.jar /app/CSVToMySQL.jar
ENTRYPOINT ["java", "-jar", "/app/CSVToMySQL.jar"]
