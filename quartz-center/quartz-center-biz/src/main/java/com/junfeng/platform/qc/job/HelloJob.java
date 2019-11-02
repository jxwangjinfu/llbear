package com.junfeng.platform.qc.job;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试任务
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月17日 上午9:14:34
 * @version 1.0
 */
@Slf4j
@DisallowConcurrentExecution
public class HelloJob extends AbstractJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 执行
     * 
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月17日 上午9:14:34
     */
    @Override
    public String run(JobExecutionContext context) throws JobExecutionException {

        log.info("class={} ,run .....................", context.getJobDetail().getJobClass());
        
        return "Hello log";

    }

}
