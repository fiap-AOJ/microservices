FROM openjdk:8-jre
MAINTAINER  Rafael Cabral
ENTRYPOINT ["java", \
           "-Xms256M", \
           "-Xmx512M", \
           "-jar", "/app/users-0.0.1-SNAPSHOT.jar"]
WORKDIR /app
EXPOSE 8089
COPY build/libs/users-0.0.1-SNAPSHOT.jar  /app/users-0.0.1-SNAPSHOT.jar