package com.junfeng.device.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 制卡机状态信息
 *
 * @author hanyx
 * @date 2019-10-24 15:11:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("data_card_printer")
public class DataCardPrinter extends Model<DataCardPrinter> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 制卡机编码
	 */
	private String dataCardPrinterSn;
	/**
	 * 制卡机型号
	 */
	private String dataCardModel;
	/**
	 * 制卡机所在电脑名称
	 */
	private String associatedComputerName;
	/**
	 * 制卡机状态
	 */
	private String printerstatus;
	/**
	 * 色带编码
	 */
	private String ribbonPartNumber;
	/**
	 * 色带剩余量
	 */
	private String ribbonRemaining;
	/**
	 * ip地址
	 */
	private String ipAddress;
	/**
	 * 制卡数量
	 */
	private String cardCount;
	/**
	 * 创建日期
	 */
	private LocalDateTime createDate;
	/**
	 * 更新日期
	 */
	private LocalDateTime updateDate;
	/**
	 * mac地址
	 */
	private String mac;

}
