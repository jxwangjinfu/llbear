package com.junfeng.platform.dc.api.feign;

import com.junfeng.platform.dc.api.feign.factory.RemoteDeviceServiceFallbackFactory;
import com.junfeng.platform.dc.api.result.DevicePageResult;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 设备服务接口
 *
 * @author: fuh
 * @createTime: 2019/10/24 16:27
 */
@FeignClient(contextId = "remoteDeviceService", value = ServiceNameConstants.DEVICE_CENTER_SERVICE, fallbackFactory = RemoteDeviceServiceFallbackFactory.class)
public interface RemoteDeviceService {

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @param from           来源
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.api.result.DevicePageResult>
	 * @author: hanyx
	 * @createTime: 2019/10/31 9:05
	 */
	@PostMapping("/device/page/deploy")
	R<DevicePageResult> getDevicePageByDeployInfo(@RequestBody DeviceDeployVo deviceDeployVo, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @param from           来源
	 * @return com.pig4cloud.pig.common.core.util.R<java.util.List < com.junfeng.platform.dc.api.result.DeviceResult>>
	 * @author: hanyx
	 * @createTime: 2019/10/29 10:34
	 */
	@PostMapping("/device/list/deploy")
	R<List<DeviceResult>> getDeviceListByDeployInfo(@RequestBody DeviceDeployVo deviceDeployVo, @RequestHeader(SecurityConstants.FROM) String from);


}
