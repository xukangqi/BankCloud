#!/bin/sh
echo "********************************************************"
echo "Starting the Config Server"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom  -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -jar /usr/local/configserver/@project.build.finalName@.jar