FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/ibe-0.0.1-SNAPSHOT.jar /app/ibe.jar
ENTRYPOINT ["java", "-jar", "/app/ibe.jar"]