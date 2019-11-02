package com.pig4cloud.pig.gateway.swagger;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * 2fx0one
 * 2019-09-10 15:06
 **/
@RestController
@RequestMapping("/swagger-resources")
@AllArgsConstructor
public class SwaggerResourceController {

	private GatewaySwaggerResourceProvider gatewaySwaggerResourceProvider;

	@RequestMapping(value = "/configuration/security")
	public ResponseEntity<SecurityConfiguration> securityConfiguration() {
		return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/ui")
	public ResponseEntity<UiConfiguration> uiConfiguration() {
		return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping
	public ResponseEntity<List<SwaggerResource>> swaggerResources() {
		return new ResponseEntity<>(gatewaySwaggerResourceProvider.get(), HttpStatus.OK);
	}
}
