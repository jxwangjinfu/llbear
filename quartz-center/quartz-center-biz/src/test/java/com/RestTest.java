/**
 * 
 */
package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.junfeng.platform.qc.QuartzCenterApplication;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月24日 下午5:39:15
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class RestTest {
    
    @Autowired
    private RestTemplate lbRestTemplate;
    
    
    @Test
    public void test() {
        
        
        
        System.out.println("---------------start---------------------");
        //
        //http://operation-center/couponquart/callback
        ResponseEntity<String> forEntity = lbRestTemplate.postForEntity("http://pig-gateway:9999/oc/couponquart/callback/30",null, String.class);
        System.out.println(forEntity);
        System.out.println("------------------end-------------------");
        
    }
    
    @Test
    public void test1() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstants.FROM,SecurityConstants.FROM_IN);
        
        ResponseEntity<String> forEntity = lbRestTemplate.postForEntity("http://operation-center/couponquart/callback/38",new HttpEntity<String>(headers), String.class);
        System.out.println(forEntity);
        System.out.println("------------------end-------------------");
    }
    

}
