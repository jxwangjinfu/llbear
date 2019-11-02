FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /device-center

WORKDIR /device-center

EXPOSE 4100

ADD ./device-center/device-center-biz/target/device-center-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar device-center-biz.jar
