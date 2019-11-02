package com.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 测试定时任务
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 下午1:49:19
 * @version 1.0
 */
public class HelloJob implements Job {

    /**
     * @param context
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月16日 下午1:49:19
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务开始......");
        System.out.println(new Date());
        System.out.println("任务结束......");

    }

}
