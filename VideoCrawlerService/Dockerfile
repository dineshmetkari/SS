FROM openjdk
VOLUME /tmp
ADD target/video-crawler-0.0.1-SNAPSHOT.jar usr/project/schematicSearch-videoCrawler-service.jar
WORKDIR usr/project/
RUN sh -c 'touch schematicSearch-videoCrawler-service.jar'
ENTRYPOINT ["java","-jar","schematicSearch-videoCrawler-service.jar"]