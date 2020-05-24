FROM openjdk:8-jre
MAINTAINER  Rafael Cabral
ENTRYPOINT ["java", \
           "-Xms256M", \
           "-Xmx512M", \
           "-jar", "/app/zuul-api-gateway-0.0.1-SNAPSHOT.jar"]
WORKDIR /app
EXPOSE 8080
COPY build/libs/zuul-api-gateway-0.0.1-SNAPSHOT.jar  /app/zuul-api-gateway-0.0.1-SNAPSHOT.jar