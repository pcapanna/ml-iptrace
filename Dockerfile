FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} iptrace.jar
ENTRYPOINT ["java","-jar","/iptrace.jar"]