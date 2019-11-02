package com.junfeng.device.manager.service;


import com.junfeng.platform.dc.api.vo.DeviceDeployVo;

import java.io.IOException;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/29 10:10
 * @projectName pig
 */
public interface DataCardReaderService {

	/**
	 * 功能描述: 导出设备列表到excel
	 *
	 * @param deviceDeployVo 查询对象
	 * @throws IOException IOException
	 * @author: hanyx
	 * @createTime: 2019/10/29 16:45
	 */
	void exportDeviceList2Excel(DeviceDeployVo deviceDeployVo) throws IOException;

}
