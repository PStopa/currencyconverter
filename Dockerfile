FROM openjdk:16
COPY build/libs/currencyconverter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080