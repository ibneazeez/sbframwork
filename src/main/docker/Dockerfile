FROM java:latest
VOLUME /tmp
ADD maven/${project.build.finalName}.jar /app.jar
EXPOSE 9000
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar