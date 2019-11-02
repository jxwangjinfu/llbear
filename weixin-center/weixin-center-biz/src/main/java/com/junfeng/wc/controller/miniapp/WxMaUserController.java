package com.junfeng.wc.controller.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.junfeng.wc.config.miniapp.WxMiniappConfiguration;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序服务端接口
 *
 * @author daiysh
 * @date 2019-09-24 11:04
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/miniapp/{appId}")
public class WxMaUserController {

	@GetMapping("/{jsCode}")
	public WxMaJscode2SessionResult getSessionInfo(@PathVariable("appId") String appId,@PathVariable("jsCode") String jsCode) throws WxErrorException {
		WxMaService wxMaService = WxMiniappConfiguration.getMpServices().get(appId);
		return wxMaService.jsCode2SessionInfo(jsCode);
	}
}
