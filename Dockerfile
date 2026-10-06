FROM eclipse-temurin:21-jre

WORKDIR /app

copy target/*.jar app.jar

EXPOSE 8080
EXPOSE 443

ENTRYPOINT ["java", "-jar", "app.jar"]