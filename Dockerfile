FROM library/tomcat:8-jre8

COPY target/Harmonizome-*.war webapps/Harmonizome.war

RUN apt-get update && apt-get install -y libtcnative-1 && apt-get clean
