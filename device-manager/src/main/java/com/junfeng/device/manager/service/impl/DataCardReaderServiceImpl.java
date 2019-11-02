package com.junfeng.device.manager.service.impl;

import com.junfeng.device.manager.service.DataCardReaderService;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/29 10:12
 * @projectName pig
 */
@Service("dataCardReaderService")
public class DataCardReaderServiceImpl implements DataCardReaderService {

	@Override
	public void exportDeviceList2Excel(DeviceDeployVo deviceDeployVo) throws IOException {

	}
}
