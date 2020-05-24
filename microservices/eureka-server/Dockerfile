FROM openjdk:8-jre
MAINTAINER  Rafael Cabral
ENTRYPOINT ["java", \
           "-Xms256M", \
           "-Xmx512M", \
           "-jar", "/app/eureka-server-0.0.1-SNAPSHOT.jar"]
WORKDIR /app
EXPOSE 8761
COPY build/libs/eureka-server-0.0.1-SNAPSHOT.jar  /app/eureka-server-0.0.1-SNAPSHOT.jar