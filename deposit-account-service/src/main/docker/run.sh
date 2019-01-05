#!/bin/sh
echo "********************************************************"
echo "Starting the Deposit Account Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom  -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.profiles.active=$PROFILE \
     -jar /usr/local/depositaccountservice/@project.build.finalName@.jar
