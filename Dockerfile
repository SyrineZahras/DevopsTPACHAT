FROM openjdk:11
EXPOSE 8089
COPY target/tpAchatProject-1.0.1-RELEASES.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]

