package miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.junfeng.wc.WeixinCenterApplication;
import com.junfeng.wc.config.miniapp.WxMiniappConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 小程序接口测试
 *
 * @author daiysh
 * @date 2019-09-26 10:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeixinCenterApplication.class)
public class WxMaUserTest {

	@Test
	public void test() throws WxErrorException {
		String appId = "wxf59a0fef55a4687d";
		WxMaService wxMaService = WxMiniappConfiguration.getMpServices().get(appId);
//		WxMaJscode2SessionResult result = wxMaService.jsCode2SessionInfo("aaa");

//		System.out.println("result:"+result+"---"+wxMaService.getAccessToken());
		System.out.println(wxMaService.getAccessToken());
	}
}
