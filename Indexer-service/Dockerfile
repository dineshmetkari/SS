FROM openjdk
VOLUME /tmp
ADD target/Indexer-service-0.0.1-SNAPSHOT.jar Indexer-service-0.0.1-SNAPSHOT.jar
EXPOSE 8051
ENTRYPOINT ["java","-jar","Indexer-service-0.0.1-SNAPSHOT.jar"]
