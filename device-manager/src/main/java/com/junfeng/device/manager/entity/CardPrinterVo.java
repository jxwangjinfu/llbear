package com.junfeng.device.manager.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 制卡机展示实体类
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/30 11:06
 * @projectName pig
 */
@Data
public class CardPrinterVo {

	/**
	 * SN编码
	 */
	@ExcelProperty(value = {"制卡机编码"})
	private String devCode;
	/**
	 * 设备具体型号
	 */
	@ExcelProperty(value = {"制卡机具号"})
	private String model;
	/**
	 * 色带编码
	 */
	@ExcelProperty(value = {"色带编码"})
	private String colorRibbonCode;
	/**
	 * 色带剩余量
	 */
	@ExcelProperty(value = {"色带剩余量"})
	private String colorRibbonRemain;
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
