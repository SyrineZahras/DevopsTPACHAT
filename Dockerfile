FROM openjdk:11
EXPOSE 8089
COPY target/tpAchatProject-1.0.4-RELEASE.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]

