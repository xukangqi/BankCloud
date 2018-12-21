#!/bin/sh
echo "********************************************************"
echo "Starting the Demo Provider"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/demoprodvider/@project.build.finalName@.jar
