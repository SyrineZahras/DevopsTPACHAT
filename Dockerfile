FROM openjdk:11
EXPOSE 8089
COPY target/tpAchatProject-1.0.2-SNAPSHOT.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]

