package com.pig4cloud.pig.admin.rabbit;

import lombok.Data;

/**
 * 消息对象
 *
 * @author daiysh
 * @date 2019-10-24 15:09
 **/
@Data
public class Message {
	private Integer userId;
	private String mobile;

	public Message(Integer userId,String mobile) {
		this.userId = userId;
		this.mobile = mobile;
	}
}
