FROM eclipse-temurin:17

LABEL mentainer="samtasahni6@gmail.com"

WORKDIR /app

COPY target/springboot.blog-rest-api-0.0.1-SNAPSHOT.jar /app/springboot.blog-rest-api.jar

ENTRYPOINT ["java", "-jar", "springboot.blog-rest-api.jar"]

