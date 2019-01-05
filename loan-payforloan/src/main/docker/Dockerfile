FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/demoprovider
ADD @project.build.finalName@.jar /usr/local/demoprovider/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
