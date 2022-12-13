FROM ubuntu

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

ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

CMD [ "/entrypoint.sh" ]
