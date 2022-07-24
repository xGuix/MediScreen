FROM openjdk:17-oracle
EXPOSE 8082
VOLUME /tmp
COPY target/assessment-1.0.0.jar app.jar
CMD ["java","-jar","/app.jar"]