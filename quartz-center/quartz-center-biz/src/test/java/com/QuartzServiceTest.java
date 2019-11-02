package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.qc.QuartzCenterApplication;
import com.junfeng.platform.qc.entity.TypeBlob;
import com.junfeng.platform.qc.service.QuartzService;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月17日 下午5:26:42
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class QuartzServiceTest {
    
    @Autowired
    private QuartzService quartzService;
    
    @Test
    public void test() {
        
        TypeBlob jobData = quartzService.getJobData("testJob02", "testGroup02");
        
        System.out.println(new String(jobData.getTblob()));
        
        
    }

}
