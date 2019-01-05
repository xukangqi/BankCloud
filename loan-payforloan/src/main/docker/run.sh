#!/bin/sh
echo "********************************************************"
echo "Starting the Demo Provider"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/demoprovider/@project.build.finalName@.jar
