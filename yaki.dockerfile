FROM openjdk:17-jdk-slim
COPY target/YakinikuWebsite-0.0.1-SNAPSHOT.jar yaki.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","yaki.jar"]