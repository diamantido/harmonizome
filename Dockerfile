FROM ubuntu as builder

RUN set -x \
  && apt-get update -y \
  && apt-get install -y unzip curl openjdk-11-jdk

RUN \
  cd /usr/local && \
  curl -L https://services.gradle.org/distributions/gradle-7.0-bin.zip -o gradle-7.0-bin.zip && \
  unzip gradle-7.0-bin.zip && \
  rm gradle-7.0-bin.zip

ENV GRADLE_HOME=/usr/local/gradle-7.0
ENV PATH=$PATH:$GRADLE_HOME/bin JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

WORKDIR /work

ADD . .
RUN gradle war

FROM library/tomcat:8-jre8

RUN apt-get update && apt-get install -y libtcnative-1 gettext && apt-get clean

ENV HARMONIZOME_PREFIX=Harmonizome
ADD ./overrides overrides
ADD ./entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

COPY --from=builder /work/build/libs/work.war Harmonizome.war

CMD [ "/entrypoint.sh" ]
