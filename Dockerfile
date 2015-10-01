FROM java:8-jdk
EXPOSE 8080
WORKDIR /app
ADD target/twitter-geo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ""
