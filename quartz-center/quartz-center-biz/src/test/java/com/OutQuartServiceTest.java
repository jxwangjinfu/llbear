package com;

import com.junfeng.platform.qc.QuartzCenterApplication;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.service.OutQuartService;
import com.junfeng.platform.qc.util.CronUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/30 11:34
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class OutQuartServiceTest {

	@Autowired
	private OutQuartService outQuartService;

	@Test
	public void test(){
		OutQuartVO outQuart = new OutQuartVO();
		outQuart.setCronExpression(CronUtils.getCron(CronUtils.getCron(LocalDateTime.now()),2));
		outQuart.setCallbackUrl("http://operation-center/quartzlog/callback/66");
		outQuart.setJobName("test005");
		outQuart.setJobGroup("test005");
		outQuart.setOutSysName("oc");
		outQuart.setTriggerName("ttt");
		outQuartService.saveOut(outQuart);

	}
}
