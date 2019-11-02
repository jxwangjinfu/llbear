package com.junfeng.platform.oc.api.feign.factory;

import com.junfeng.platform.oc.api.feign.OcRemoteService;
import com.junfeng.platform.oc.api.feign.fallback.OcRemoteServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 *
 * @projectName:pig
 * @author:Wangjf
 * @date:2019/10/14 17:22
 * @version 1.0
 */
@Component
public class OcRemoteServiceFallbackFactory implements FallbackFactory<OcRemoteService> {

    @Override
    public OcRemoteService create(Throwable throwable) {
        OcRemoteServiceFallbackImpl ocRemoteServiceFallback = new OcRemoteServiceFallbackImpl();
        ocRemoteServiceFallback.setCause(throwable);
        return ocRemoteServiceFallback;
    }
}
