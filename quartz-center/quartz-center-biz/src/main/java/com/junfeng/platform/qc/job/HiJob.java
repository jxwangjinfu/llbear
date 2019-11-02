package com.junfeng.platform.qc.job;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试类
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月17日 上午10:50:39
 * @version 1.0
 */
@Slf4j
@DisallowConcurrentExecution
public class HiJob extends AbstractJob implements Serializable {

    private static final long serialVersionUID = 2509046400507811448L;

    /**
     * @param context
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月17日 上午10:50:39
     */
    @Override
    public String run(JobExecutionContext context) throws JobExecutionException {
        

        log.info("class={} , param = {}", context.getJobDetail().getJobClass(),
                context.getJobDetail().getJobDataMap().getString("param"));

        log.info("  HI job running ............................");

        return "HI job";

    }

}
