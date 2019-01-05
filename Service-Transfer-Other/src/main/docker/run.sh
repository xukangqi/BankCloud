#!/bin/sh
echo "********************************************************"
echo "Starting Transfer Other Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom \
     -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -Dspring.datasource.url=$DATABASE_URI \
     -Dspring.datasource.username=$USERNAME \
     -Dspring.datasource.password=$PASSWORD \
     -jar /usr/local/transferother/@project.build.finalName@.jar
