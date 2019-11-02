package com.junfeng.platform.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-18 09:52
 * @projectName pig
 */
//@EnableAutoConfiguration
@ConditionalOnProperty(name = "swagger2.enable", havingValue = "true")
@Configuration
@EnableSwagger2
public class Swagger2AutoConfiguration {

	//开发和测试使用的接口文档自动生成器工具 访问 http://ip:port/swagger-ui.html
	@Bean
	public Docket createRestApi() {
		System.out.println("=== ！！！启动 swagger 线上不应该看到该组件的注册！！！=== ");
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
//                .apis(RequestHandlerSelectors.basePackage("com.junhe.ec"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
			.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
			.paths(PathSelectors.any())
			.build()
			.securitySchemes(Arrays.asList(new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header")))
//                .globalOperationParameters(Arrays.asList(
//                        new ParameterBuilder()
//                                .name("token")
//                                .description("前端的token")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header").required(false)
//                                .defaultValue("")
//                                .build(),
//                        new ParameterBuilder()
//                                .name("Authorization")
//                                .description("后台的token")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header").required(false)
//                                .defaultValue("")
//                                .build()
//                ))
			.apiInfo(new ApiInfoBuilder()
				.title("Swagger2 RESTful APIs")
				.description("restful APIs， 根据代码自动生成的api文档和接口！")
				.termsOfServiceUrl("JunFeng Tech")
				.version("1.0")
				.build());
	}

}
