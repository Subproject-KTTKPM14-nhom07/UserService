FROM openjdk:11
LABEL maintainer="userservice"
ADD target/User-0.0.1-SNAPSHOT.jar springboot-docker-userservice.jar
ENTRYPOINT ["java","-jar","springboot-docker-userservice.jar"]