FROM openjdk:17-oracle
EXPOSE 8080
VOLUME /tmp
COPY target/patient-1.0.0.jar app.jar
CMD ["java","-jar","/app.jar"]