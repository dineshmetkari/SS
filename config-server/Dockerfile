FROM openjdk
VOLUME /tmp
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ADD src/main/resources/shared /usr/project/shared
EXPOSE 8888
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]
