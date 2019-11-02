package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.qc.QuartzCenterApplication;
import com.junfeng.platform.qc.config.QuartzProperties;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月18日 下午2:19:31
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class QuartzPropertiesTest {
    
    @Autowired
    private QuartzProperties quartzProperties;
    
    @Test
    public void test() {
        
        System.out.println(quartzProperties.getJobStoreClass());
        System.out.println(quartzProperties.getSchedulerInstanceName());
    };

}
