package com.junfeng.platform.qc.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.junfeng.platform.qc.api.feign.factory.RemoteQuartzServiceFallbackFactory;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;

/**
 * 对外暴露接口
 * 
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月24日 上午11:09:31
 * @version 1.0
 */
@FeignClient(contextId = "remoteQuartzService", value = ServiceNameConstants.QUARTZ_CENTER_SERVICE, fallbackFactory = RemoteQuartzServiceFallbackFactory.class)
public interface RemoteQuartzService {


    @GetMapping("/log/{name}")
    R<String> getLog(@PathVariable("name") String name, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 保存定时任务
     * @param outQuartVO
     * @param from
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:56:51
     */
    @PostMapping("/outquart/out")
    public R<Boolean> saveOut(@RequestBody OutQuartVO outQuartVO, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 修改定时任务
     * @param outQuartVO
     * @param from
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:57:01
     */
    @PutMapping("/outquart/out")
    public R<Boolean> updateOut(@RequestBody OutQuartVO outQuartVO, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     * @param from
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:57:10
     */
    @DeleteMapping("/outquart/out/{jobName}/{jobGroup}")
    public R<Boolean> removeOut(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup,
            @RequestHeader(SecurityConstants.FROM) String from);

}
