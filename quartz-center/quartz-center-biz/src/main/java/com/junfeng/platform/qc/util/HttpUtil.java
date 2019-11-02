package com.junfeng.platform.qc.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;

/**
 * HTTP工具类
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月26日 下午1:33:35
 * @version 1.0
 */
public class HttpUtil {
    
    /**
     * 获取内部调用的头
     * @return
     * @author:Wangjf
     * @createTime:2019年9月26日 下午1:36:23
     */
    public static HttpEntity<String> getInnerHttpHead(){
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstants.FROM,SecurityConstants.FROM_IN);
        return new HttpEntity<String>(headers);
        
    }

}
