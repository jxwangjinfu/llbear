package com.junfeng.platform.qc.job;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.junfeng.platform.qc.service.OutQuartService;

import lombok.extern.slf4j.Slf4j;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月25日 下午3:08:32
 * @version 1.0
 */
@Slf4j
public class NotifyOutJob extends AbstractJob implements Serializable {
    private static final long serialVersionUID = -203921939416184169L;
    @Autowired
    private OutQuartService outQuartService;

    /**
     * @param context
     * @return
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午3:08:32
     */
    @Override
    String run(JobExecutionContext context) throws JobExecutionException {

        String name = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();
        log.info("run job name={},group={}", name, group);
        Boolean result = outQuartService.updateState(name, group);
        if (result) {
            return "执行成功！";
        }
        return "执行失败";
    }

}
