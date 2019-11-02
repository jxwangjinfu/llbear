package com.junfeng.wc.config.mp;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author daiysh
 * @date 2019-09-25 22:08
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
	private static Map<String, WxMpService> mpServices = Maps.newHashMap();

	public static Map<String, WxMpService> getMpServices() {
		return mpServices;
	}

	@Autowired
	private WxMpProperties properties;

	@Autowired
	private WxMpInMemoryConfigStorage configStorage;

	@PostConstruct
	public void initServices() {
		// 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
		final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
		if (configs == null) {
			throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
		}

		mpServices = configs.stream().map(a -> {
			//memory
			configStorage.setAppId(a.getAppId());
			configStorage.setSecret(a.getSecret());
			configStorage.setToken(a.getToken());
			configStorage.setAesKey(a.getAesKey());

			WxMpService service = new WxMpServiceImpl();
			service.setWxMpConfigStorage(configStorage);
			log.info("配置的appId={}",a.getAppId());
			return service;
		}).collect(Collectors.toMap(s -> s.getWxMpConfigStorage().getAppId(), a -> a, (o, n) -> o));
	}
}
