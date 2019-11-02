package com.junfeng.device.manager.controller;

import com.junfeng.device.manager.constant.DeviceConstant;
import com.junfeng.device.manager.entity.CardPrinterVo;
import com.junfeng.device.manager.entity.ColorRibbonVo;
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
 * 制卡机管理
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/29 16:40
 * @projectName pig
 */
@Api(tags = {"制卡机管理"})
@RestController
@AllArgsConstructor
@RequestMapping("/cardprinter")
public class DataCardPrinterController {

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
		deviceDeployVo.setDeviceTypeCode(DeviceConstant.TYPE_CARD_PRINTER);
		return R.ok(remoteDeviceService.getDevicePageByDeployInfo(deviceDeployVo, SecurityConstants.FROM_IN).getData());
	}

	/**
	 * 功能描述: 导出设备列表
	 *
	 * @param deviceDeployVo 查询对象
	 * @author: hanyx
	 * @createTime: 2019/10/29 9:34
	 */
	@ApiOperation(value = "导出设备列表到excel", notes = "deviceDeployVo", produces = "application/octet-stream")
	@GetMapping("/export")
	public void exportDeviceList2Excel(DeviceDeployVo deviceDeployVo, HttpServletResponse response) throws IOException {
		// FIXME:带上制卡机设备类型编码，真实的制卡机类型编码可能不是这个
		deviceDeployVo.setDeviceTypeCode(DeviceConstant.TYPE_CARD_PRINTER);
		List<DeviceResult> deviceList = remoteDeviceService.getDeviceListByDeployInfo(deviceDeployVo, SecurityConstants.FROM_IN).getData();
		if (deviceList == null) {
			return;
		}

		List<CardPrinterVo> cardPrinterVoList = deviceList.stream().map(device -> {
			CardPrinterVo cardPrinterVo = new CardPrinterVo();
			BeanUtils.copyProperties(device, cardPrinterVo);
			// 获取色带信息
			ColorRibbonVo colorRibbonVo = StringUtil.getColorRibbon(device.getOtherPropertyJson());
			if (colorRibbonVo != null) {
				cardPrinterVo.setColorRibbonCode(colorRibbonVo.getColorRibbonCode());
				cardPrinterVo.setColorRibbonRemain(colorRibbonVo.getColorRibbonRemain());
			}
			return cardPrinterVo;
		}).collect(Collectors.toList());

		response.setContentType("application/octet-stream;charset=utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=cardPrinterList.xlsx");
		response.setHeader("Pragma", "public");

		ExcelUtil.writeListTo(response.getOutputStream(), cardPrinterVoList, CardPrinterVo.class);
	}

}
