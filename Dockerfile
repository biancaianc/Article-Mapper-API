FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/article-mapper-0.0.1-SNAPSHOT.jar article.jar
ENTRYPOINT ["java","-jar","/article.jar"]
