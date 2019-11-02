package com.junfeng.platform.csc.controller.model;

import lombok.Data;

/**
 * 工单处理结果提交
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/9 14:27
 * @projectName pig
 */
@Data
public class OrderRecordVo {
	/**
	 * 工单记录表流水编号
	 */
	private Long orderRecordId;
	/**
	 * 处理结果类型编码
	 */
	private String reasonTypeCode;
	/**
	 * 处理详细信息
	 */
	private String orderDetail;
	/**
	 * 下一个处理组 0:表示处理完结
	 */
	private Long nextGroupId;
}
