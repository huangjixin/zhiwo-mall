FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /u06/ceta/pkg/bhmall-admin-root/share
ADD *.jar app.jar
#RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xmx300m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dserver.port=8773 -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]