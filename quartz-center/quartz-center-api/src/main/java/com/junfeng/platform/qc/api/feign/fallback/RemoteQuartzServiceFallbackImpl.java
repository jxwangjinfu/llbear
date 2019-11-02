package com.junfeng.platform.qc.api.feign.fallback;

import org.springframework.stereotype.Component;

import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.pig4cloud.pig.common.core.util.R;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月24日 上午11:13:15
 * @version 1.0
 */
@Slf4j
@Component
public class RemoteQuartzServiceFallbackImpl implements RemoteQuartzService {
    @Setter
    private Throwable cause;

    @Override
    public R<String> getLog(String name, String from) {
        log.error("feign 查询日志失败", cause);
        return R.failed("查询失败");
    }

    @Override
    public R<Boolean> saveOut(OutQuartVO outQuartVO, String from) {
        log.error("feign 插入外部定时任务失败,outQuartVO={},from={}", outQuartVO, from, cause);
        return R.feignFailed(false);
    }

    @Override
    public R<Boolean> updateOut(OutQuartVO outQuartVO, String from) {
        log.error("feign 修改外部定时任务失败,outQuartVO={},from={}", outQuartVO, from, cause);
		return R.feignFailed(false);
    }

    @Override
    public R<Boolean> removeOut(String jobName, String jobGroup, String from) {
        log.error("feign 删除外部定时任务失败,jobName={},jobGroup={},from={}", jobName, jobGroup, from, cause);
		return R.feignFailed(false);
    }

}
