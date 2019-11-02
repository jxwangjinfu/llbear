package com.junfeng.wc.config.open;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import com.google.common.collect.Maps;
import com.junfeng.wc.config.miniapp.WxMaInMemoryConfigStorage;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties(WxMiniappProperties.class)
public class WxOpenConfiguration {
	private static Map<String, WxMaService> mpServices = Maps.newHashMap();

	public static Map<String, WxMaService> getMpServices() {
		return mpServices;
	}

	@Autowired
	private WxMiniappProperties properties;

	@Autowired
	private WxMaInMemoryConfigStorage configStorage;

	@PostConstruct
	public void initServices() {
		// 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
		final List<WxMiniappProperties.MiniappConfig> configs = this.properties.getConfigs();
		if (configs == null) {
			throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
		}

		mpServices = configs.stream().map(a -> {
			//memory
			configStorage.setAppid(a.getAppId());
			configStorage.setSecret(a.getSecret());
			configStorage.setToken(a.getToken());
			configStorage.setAesKey(a.getAesKey());

			WxMaService service = new WxMaServiceImpl();
			service.setWxMaConfig(configStorage);
			log.info("配置的appId={}",a.getAppId());
			return service;
		}).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a, (o, n) -> o));
	}
}
