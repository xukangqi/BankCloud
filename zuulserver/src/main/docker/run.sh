#!/bin/sh
echo "********************************************************"
echo "Starting Zuul Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     -jar /usr/local/zuulservice/@project.build.finalName@.jar
