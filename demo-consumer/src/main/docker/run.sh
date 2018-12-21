#!/bin/sh
echo "********************************************************"
echo "Starting the Demo Consumer"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/democonsumer/@project.build.finalName@.jar
