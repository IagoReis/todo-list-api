FROM adoptopenjdk/openjdk11-openj9:alpine-slim

ARG JAR_FILE

RUN mkdir /opt/shareclasses

RUN mkdir /opt/app

COPY ${JAR_FILE} /opt/app/app.jar

CMD ["java", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-Xscmx128m", "-Xscmaxaot100m", "-Xshareclasses:cacheDir=/opt/shareclasses", "-jar", "/opt/app/app.jar"]