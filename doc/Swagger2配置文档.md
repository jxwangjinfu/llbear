# Swagger 配置文档

Author: wangk

Summary：

场景描述：

​			开发人员在写完接口后，在方法上加入 @ApiOperation 注解。
            导入mvn坐标后。
​            可以快速开始接口测试。

配置步骤

 1. 配置 pom.xml 文件

    ```xml
    		<!--swagger 配置-->
    		<dependency>
    			<groupId>com.junfeng</groupId>
    			<artifactId>junfeng-common-swagger2</artifactId>
    			<version>0.0.1</version>
    			<scope>compile</scope>
    		</dependency>
    ```
    
    
    
（已删除第2步 @EnableJunFengSwagger2。 导入mvn坐标即表示启动。不需要该注解。)
 2. Application 启动类加上注解 @EnableJunFengSwagger 
   
    ```java
    
        @EnableJunFengSwagger2
        public class TradeCenterApplication {...}
    
    ```

 3. 打开网关的或者对应端口的swagger-ui.html 即可放心食用啦！
