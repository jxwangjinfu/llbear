package com.junfeng.device.manager.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 读卡器展示实体类
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/30 10:02
 * @projectName pig
 */
@Data
public class CardReaderVo {
	/**
	 * SN编码
	 */
	@ExcelProperty(value = {"SN编码"})
	private String devCode;
	/**
	 * 读卡器SN编码的前一部分
	 */
	@ExcelProperty(value = {"设备机具号"})
	private String snPart1;
	/**
	 * 读卡器SN编码的后一部分
	 */
	@ExcelProperty(value = {"PSAM"})
	private String snPart2;
	/**
	 * 设备版本号
	 */
	@ExcelProperty(value = {"机器版本号"})
	private String currentVersionName;
	/**
	 * IP地址
	 */
	@ExcelProperty(value = {"IP地址"})
	private String lanIp;
	/**
	 * mac地址
	 */
	@ExcelProperty(value = {"MAC地址"})
	private String mac;
	/**
	 * 更新时间
	 */
	@ExcelProperty(value = {"更新时间"})
	private LocalDateTime updateTime;
}
