FROM java:8
ADD ${project.build.finalName}.jar app.jar
EXPOSE 8888
ENTRYPOINT  ["java", "-jar", "/app.jar"]