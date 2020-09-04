FROM ubuntu:20.04

ADD setup.sh /setup.sh

ENV TZ=Europe/Istanbul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN chmod a+rx setup.sh
RUN ./setup.sh java8

ENV JAVA_HOME /opt/openjdk
ENV PATH $JAVA_HOME/bin:$PATH
ADD docker-lib.sh /docker-lib.sh

ADD build-release-scripts.sh /build-release-scripts.sh
ADD releasescripts /release-scripts
RUN chmod a+rx build-release-scripts.sh
RUN ./build-release-scripts.sh


EXPOSE 8089


ENTRYPOINT ["java","-jar","app.jar"]
