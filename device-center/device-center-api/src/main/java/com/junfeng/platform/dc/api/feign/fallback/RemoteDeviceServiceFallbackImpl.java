package com.junfeng.platform.dc.api.feign.fallback;

import com.junfeng.platform.dc.api.feign.RemoteDeviceService;
import com.junfeng.platform.dc.api.result.DevicePageResult;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author daiysh
 * @date 2019/10/12
 * feign token  fallback
 */
@Slf4j
@Component
public class RemoteDeviceServiceFallbackImpl implements RemoteDeviceService {
	@Setter
	private Throwable cause;

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @param from           来源
	 * @return com.pig4cloud.pig.common.core.util.R<com.baomidou.mybatisplus.core.metadata.IPage < com.junfeng.platform.dc.api.result.DeviceResult>>
	 * @author: hanyx
	 * @createTime: 2019/10/30 15:21
	 */
	@Override
	public R<DevicePageResult> getDevicePageByDeployInfo(DeviceDeployVo deviceDeployVo, String from) {
		log.error("feign 按设备及其部署信息分页查询失败", cause);
		log.error("查询条件={}", deviceDeployVo);
		return R.feignFailed("失败");
	}

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param vo   查询对象
	 * @param from 来源
	 * @return com.pig4cloud.pig.common.core.util.R<java.util.List < com.junfeng.platform.dc.api.result.DeviceResult>>
	 * @author: hanyx
	 * @createTime: 2019/10/29 10:34
	 */
	@Override
	public R<List<DeviceResult>> getDeviceListByDeployInfo(DeviceDeployVo vo, String from) {
		log.error("feign 按设备及其部署信息查询失败", cause);
		log.error("查询条件={}", vo);
		return R.feignFailed("失败");
	}
}
