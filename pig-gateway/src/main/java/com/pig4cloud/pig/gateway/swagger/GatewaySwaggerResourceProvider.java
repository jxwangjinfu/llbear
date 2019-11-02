package com.pig4cloud.pig.gateway.swagger;

import com.pig4cloud.pig.common.core.config.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2fx0one
 * 2019-09-10 15:02
 **/
@Component
public class GatewaySwaggerResourceProvider implements SwaggerResourcesProvider {
	/**
	 * swagger2默认的url后缀
	 */
	private static final String SWAGGER2URL = "/v2/api-docs";

	/**
	 * 网关路由
	 */
	private RouteLocator routeLocator;

	/**
	 * 网关应用名称
	 */
	@Value("${spring.application.name}")
	private String self;

	@Autowired
	private FilterIgnorePropertiesConfig ignorePropertiesConfig;

	@Autowired
	public GatewaySwaggerResourceProvider(RouteLocator routeLocator) {
		this.routeLocator = routeLocator;
	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<String> routeHosts = new ArrayList<>();
		// 由于我的网关采用的是负载均衡的方式，因此我需要拿到所有应用的serviceId
		// 获取所有可用的host：serviceId
		routeLocator.getRoutes()
			.filter(route -> route.getId() != null)
			.filter(route -> !self.equals(route.getId()))
			.subscribe(route -> routeHosts.add(route.getId()));


		//拼接url，样式为/serviceId/v2/api-info，当网关调用这个接口时，会自动通过负载均衡寻找对应的主机
		List<String> swaggerProviders = ignorePropertiesConfig.getSwaggerProviders();
		return routeHosts.stream()
			.distinct()
			.filter(s -> !swaggerProviders.contains(s))
			.map(GatewaySwaggerResourceProvider::createSwaggerResource)
			.collect(Collectors.toList());

	}

	private static SwaggerResource createSwaggerResource(String name) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation("/" + name + SWAGGER2URL);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
}
