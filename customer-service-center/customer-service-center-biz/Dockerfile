FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /customer-service-center

WORKDIR /customer-service-center

EXPOSE 4800

ADD ./customer-service-center/customer-service-center-biz/target/customer-service-center-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar customer-service-center-biz.jar
