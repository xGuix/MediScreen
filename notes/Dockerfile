FROM openjdk:17-oracle
EXPOSE 8081
VOLUME /tmp
COPY target/notes-1.0.0.jar app.jar
CMD ["java","-jar","/app.jar"]