package com.junfeng.device.manager.controller;

import com.junfeng.device.manager.constant.DeviceConstant;
import com.junfeng.device.manager.entity.CardReaderVo;
import com.junfeng.device.manager.util.StringUtil;
import com.junfeng.platform.dc.api.feign.RemoteDeviceService;
import com.junfeng.platform.dc.api.result.DevicePageResult;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.junfeng.platform.excel.ExcelUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 读卡器管理
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/24 10:45
 * @projectName pig
 */
@Api(tags = {"读卡器管理"})
@RestController
@AllArgsConstructor
@RequestMapping("/cardreader")
public class DataCardReaderController {

	private final RemoteDeviceService remoteDeviceService;


	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.api.result.DevicePageResult>
	 * @author: hanyx
	 * @createTime: 2019/10/31 9:41
	 */
	@ApiOperation(value = "按设备及其部署信息分页查询", notes = "参数： page 分页对象 和 deviceDeployVo")
	@GetMapping("/page/deploy")
	public R<DevicePageResult> getDevicePageByDeployInfo(DeviceDeployVo deviceDeployVo) {
		deviceDeployVo.setDeviceTypeCode(DeviceConstant.TYPE_CARD_READER);
		return R.ok(remoteDeviceService.getDevicePageByDeployInfo(deviceDeployVo, SecurityConstants.FROM_IN).getData());
	}

	/**
	 * 功能描述: 导出设备列表到excel
	 *
	 * @param deviceDeployVo 查询对象
	 * @author: hanyx
	 * @createTime: 2019/10/29 9:34
	 */
	@ApiOperation(value = "导出设备列表到excel", notes = "deviceDeployVo", produces = "application/octet-stream")
	@GetMapping("/export")
	public void exportDeviceList2Excel(DeviceDeployVo deviceDeployVo, HttpServletResponse response) throws IOException {
		// 带上读卡器设备类型编码
		deviceDeployVo.setDeviceTypeCode(DeviceConstant.TYPE_CARD_READER);
		List<DeviceResult> deviceList = remoteDeviceService.getDeviceListByDeployInfo(deviceDeployVo, SecurityConstants.FROM_IN).getData();
		if (deviceList == null) {
			return;
		}

		List<CardReaderVo> cardReaderVoList = deviceList.stream().map(device -> {
			CardReaderVo cardReaderVo = new CardReaderVo();
			BeanUtils.copyProperties(device, cardReaderVo);
			cardReaderVo.setSnPart1(StringUtil.getSnPart1(device.getDevCode()));
			cardReaderVo.setSnPart2(StringUtil.getPSAM(device.getDevCode()));
			return cardReaderVo;
		}).collect(Collectors.toList());

		response.setContentType("application/octet-stream;charset=utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=cardReaderList.xlsx");
		response.setHeader("Pragma", "public");

		ExcelUtil.writeListTo(response.getOutputStream(), cardReaderVoList, CardReaderVo.class);
	}


}
