package mp;

import com.google.common.collect.Lists;
import com.junfeng.wc.WeixinCenterApplication;
import com.junfeng.wc.config.mp.WxMpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 公众号测试
 *
 * @author daiysh
 * @date 2019-09-26 11:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeixinCenterApplication.class)
@Slf4j
public class WxMpTest {

	@Test
	public void getMpUser() {
		String appid = "wxf2a8098c6e7e3df7"; // 公众号：june，开发者ID
		String code = "111";
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

	@Test
	public void sendMsg() throws WxErrorException {
		String appid = "wxf2a8098c6e7e3df7";
		WxMpService wxMpService = WxMpConfiguration.getMpServices().get(appid);

		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		// fHGEzOIKsrntqtXLCl4uCYJ1kbxh9Zd-M_S2kE-YZII
		templateMessage.setTemplateId("fHGEzOIKsrntqtXLCl4uCYJ1kbxh9Zd-M_S2kE-YZII");
		templateMessage.setMiniProgram(null);
		templateMessage.setToUser("oW7y001iQKZ3GD1eTRB34J9I8HpA");
		templateMessage.setUrl("url");
		List<WxMpTemplateData> data = Lists.newArrayList();
		templateMessage.setData(data);


		wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	}

	@Test
	public void userList() throws WxErrorException {
		String appid = "wxf2a8098c6e7e3df7";
		WxMpService wxMpService = WxMpConfiguration.getMpServices().get(appid);

		WxMpUserList userList = wxMpService.getUserService().userList(null);

		for (String e : userList.getOpenids()) {
			WxMpUser user = wxMpService.getUserService().userInfo(e);

			System.out.println(user.getOpenId()+"-----"+user.getNickname());
		}

		WxMpTest wxMpTest = new WxMpTest();
	}
}
