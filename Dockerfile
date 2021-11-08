FROM openjdk:11.0.7-jre-slim-buster
LABEL maintainer="Gabriel Marte <20170388@ce.pucmm.edu.do>"
VOLUME /tmpJMS
ENV MODE=99 ID=99
EXPOSE 61616
COPY /build/libs/*.jar mi_app.jar
ENTRYPOINT java -jar mi_app.jar $MODE $ID 3
