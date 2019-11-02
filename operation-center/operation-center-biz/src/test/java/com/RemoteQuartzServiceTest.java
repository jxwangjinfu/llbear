package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.qc.api.feign.RemoteQuartzService;

/**
 * @projectName:operation-center-biz
 * @author:Wangjf
 * @date:2019年9月24日 下午2:05:02
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class RemoteQuartzServiceTest {
    
    @Autowired
    private RemoteQuartzService remoteQuartzService;
    
    @Test
    public void test() {
    }

}
