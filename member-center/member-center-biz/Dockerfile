FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /member-center

WORKDIR /member-center

EXPOSE 4100

ADD ./member-center/member-center-biz/target/member-center-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar member-center-biz.jar
