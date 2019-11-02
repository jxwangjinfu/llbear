package com.junfeng.platform.dc.quart;

import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.errorwarner.DingTalkMessageType;
import com.junfeng.platform.errorwarner.ErrorVoBuilder;
import com.junfeng.platform.errorwarner.NotifyType;
import com.junfeng.platform.errorwarner.sender.ErrorWarnerSender;
import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: hanyx
 * @CreateTime: 2019-09-29 10:15:42
 * @Description: 应用启动时执行的任务
 */

@Order
@Component
@AllArgsConstructor
@Slf4j
public class InitTask implements CommandLineRunner {

	// 远程定时任务服务
	private final RemoteQuartzService remoteQuartzService;

	private ErrorWarnerSender errorWarnerSender;

	@Override
	public void run(String... args) throws Exception {
		startUpdateDeviceOnlineStateQuart();
	}

	/**
	 * 启动更新设备在线状态的定时任务
	 */
	private void startUpdateDeviceOnlineStateQuart() {
		String jobName = DeviceCenterConstant.SERVICE_NAME + System.currentTimeMillis();
		OutQuartVO startOutQuartVO = new OutQuartVO();
		startOutQuartVO.setOutSysName(DeviceCenterConstant.SERVICE_NAME);
		startOutQuartVO.setCallbackUrl(DeviceCenterConstant.DEVICE_CALLBACK_JOB_URL);
		// 每2分钟执行一次任务
		startOutQuartVO.setCronExpression(DeviceCenterConstant.PERIOD_TIME_RULE);
		startOutQuartVO.setJobGroup(jobName);
		startOutQuartVO.setJobName(jobName);
		startOutQuartVO.setTriggerName("trigger" + System.currentTimeMillis());
		startOutQuartVO.setParam(startOutQuartVO.getJobName() + startOutQuartVO.getJobGroup());
		try {
			R<Boolean> booleanR = remoteQuartzService.saveOut(startOutQuartVO, SecurityConstants.FROM_IN);
			if(booleanR.getCode()!=200){
				log.error(booleanR.toString());
				errorWarnerSender.send(ErrorVoBuilder.anErrorVo().withNotifyType(NotifyType.DINGTALK)
					.withDingTalkMessageType(DingTalkMessageType.LINK).withCode("100").withMessage("Quartz服务异常")
					.withUrl("www.baidu.com")
					.withSendMailTo("1397710623@qq.com").build());
			}
		}catch (Exception e){
			log.error("保存Quartz失败",e);
			errorWarnerSender.send(ErrorVoBuilder.anErrorVo().withCode("100").withMessage("Quartz服务异常").withSendMailTo("1397710623@qq.com").build());
		}
		Quart.jobName = jobName;
		Quart.jobGroup = jobName;
	}
}
