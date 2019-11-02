/**
 * 
 */
package com.junfeng.platform.qc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/{name}")
    public R<String> getById(@PathVariable("name") String name) {

        System.out.println("调用-------------------------"+name);
        return R.ok(name);
    }
    

}
