package com.junfeng.platform.dc.quart;

import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Author: hanyx
 * @CreateTime: 2019-09-29 10:20:57
 * @Description: 应用结束时执行的任务
 */
@Component
@AllArgsConstructor
public class EndTask {

	// 远程定时任务服务
	private final RemoteQuartzService remoteQuartzService;

	@PreDestroy
	public void closeUpdateDeviceOnlineStateQuart() {
		// 删除定时任务
		remoteQuartzService.removeOut(Quart.jobName, Quart.jobGroup, SecurityConstants.FROM_IN);
	}

}
