package com.junfeng.weixin.server.api.feign;

import com.junfeng.weixin.server.api.feign.factory.RemoteWxMaUserServiceFallbackFactory;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "remoteWxMaUserService", value = ServiceNameConstants.WECHAT_CENTER_SERVICE, fallbackFactory = RemoteWxMaUserServiceFallbackFactory.class)
public interface RemoteWxMaUserService {

}
