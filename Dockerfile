FROM library/tomcat:8-jre8

ARG HARMONIZOME_PREFIX=Harmonizome

ENV DB_URL "jdbc:mysql://localhost/harmonizome"
ENV DB_USER "harmonizome"
ENV DB_PASS "harmonizome"

ADD build/libs/harmonizome*.war Harmonizome.war
RUN set -x \
    && mv "Harmonizome.war" "webapps/${HARMONIZOME_PREFIX}.war"

RUN apt-get update && apt-get install -y libtcnative-1 && apt-get clean
