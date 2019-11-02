FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /shop-center

WORKDIR /shop-center

EXPOSE 4500

ADD ./shop-center/shop-center-biz/target/shop-center-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar shop-center-biz.jar
