package com.junfeng.wc.config.open;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 *
 *
 * @author daiysh
 * @date 2019-09-25 22:00
 **/
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
class WxMiniappProperties {
	private List<MiniappConfig> configs;

	@Data
	public static class MiniappConfig {
		/**
		 * 设置微信公众号的appid
		 */
		private String appId;

		/**
		 * 设置微信公众号的app secret
		 */
		private String secret;

		/**
		 * 设置微信公众号的token
		 */
		private String token;

		/**
		 * 设置微信公众号的EncodingAESKey
		 */
		private String aesKey;
	}

}
