FROM library/tomcat:8-jre8

ARG HARMONIZOME_PREFIX=/Harmonizome
RUN set -x \
    && mv "Harmonizome.war" "webapps${HARMONIZOME_PREFIX}.war"

RUN apt-get update && apt-get install -y libtcnative-1 && apt-get clean
