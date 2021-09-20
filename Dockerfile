FROM openjdk:16
EXPOSE 8080
ADD target/student-registration-docker.jar student-registration-docker.jar
ENTRYPOINT ["java", "-jar","/student-registration-docker.jar"]