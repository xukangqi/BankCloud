#!/bin/sh
echo "********************************************************"
echo "Starting the Customer Get One Customer Services"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom  -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -jar /usr/local/getonecustomer/@project.build.finalName@.jar