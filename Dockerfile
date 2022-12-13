FROM library/tomcat:8-jre8

RUN apt-get update && apt-get install -y libtcnative-1 gettext && apt-get clean

ENV HARMONIZOME_PREFIX=Harmonizome
ADD ./overrides overrides
ADD ./entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ADD build/libs/work.war Harmonizome.war

CMD /entrypoint.sh
