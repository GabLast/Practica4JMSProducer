
FROM openjdk:11.0.7-jre-slim-buster
LABEL maintainer="Gabriel Marte <20170388@ce.pucmm.edu.do>"
#ENV mode=99 id=99
VOLUME /tmpJMS
EXPOSE 61616
COPY /build/libs/*.jar mi_app.jar
ENTRYPOINT java -jar mi_app.jar $mode $id
