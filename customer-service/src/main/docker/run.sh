#!/bin/sh
echo "********************************************************"
echo "Starting the Customer Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom  -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -jar /usr/local/customerservice/@project.build.finalName@.jar