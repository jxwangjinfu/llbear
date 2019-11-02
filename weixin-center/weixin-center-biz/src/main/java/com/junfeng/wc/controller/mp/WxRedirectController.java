package com.junfeng.wc.controller.mp;

import cn.hutool.json.JSON;
import com.junfeng.wc.config.mp.WxMpConfiguration;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公众号跳转
 *
 * @author daiysh
 * @date 2019-09-26 09:25
 **/
@Api(tags = {"公众号"})
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mp/{appid}")
public class WxRedirectController {
	/**
	 * 获取用户信息
	 */
	@RequestMapping("/getUserInfo")
	public void getBase(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid, @RequestParam("code") String code) {
		WxMpService mpService = WxMpConfiguration.getMpServices().get(appid);
		try {
			WxMpOAuth2AccessToken accessToken = mpService.oauth2getAccessToken(code);
			log.info("accessToken={}", accessToken);
			WxMpUser wxMpUser = mpService.oauth2getUserInfo(accessToken, null);
			log.info("wxMpUser={}", wxMpUser);
		} catch (Exception e) {
			log.error("获取用户信息异常！", e);
		}
	}
}
