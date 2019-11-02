/**
 * 
 */
package com.junfeng.platform.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月24日 下午4:30:43
 * @version 1.0
 */
@RestController
@RequestMapping("/log")
public class LogController {
    
    @Autowired
    private RemoteQuartzService remoteQuartzService;
    
    @GetMapping("/{name}")
    public R<String> getById(@PathVariable("name") String name) {

        R<String> log = remoteQuartzService.getLog(name+"->qz", SecurityConstants.FROM_IN);
        return R.ok(log.getData());
    }
    
    @GetMapping("/user/{name}")
    public R<String> getUser(@PathVariable("name") String name) {
        
        System.out.println("入参数-------"+name);

        return R.ok(name);
    }
    

}
