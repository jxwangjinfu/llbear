package com.junfeng.platform.csc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/24 15:35
 * @projectName pig
 */
@AllArgsConstructor
public enum RoleType {
	Phone("电话",1),
	Online("在线",2),
	WorkOrder("工单",3),
	Order("订单",4);

	@Getter
	private String name;
	@Getter
	private int roleId;
}
