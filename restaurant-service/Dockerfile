FROM openjdk
VOLUME /tmp
ADD target/restaurant-restt-0.0.1-SNAPSHOT.jar usr/project/restaurant-product-app.jar
WORKDIR usr/project/
RUN sh -c 'touch restaurant-product-app.jar'
ENTRYPOINT ["java","-jar","restaurant-product-app.jar"]