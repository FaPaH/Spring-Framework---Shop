FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} SpringLab2.war
ENTRYPOINT ["java","-jar","/SpringLab2.war"]