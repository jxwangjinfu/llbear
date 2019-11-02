package com.pig4cloud.pig.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2fx0one
 * 2019-09-09 11:38
 **/

@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
	@Value("${test.ip}")
	private String ip;

	@GetMapping(value = "/ip")
	public String ip() {
		return ip;
	}
}
