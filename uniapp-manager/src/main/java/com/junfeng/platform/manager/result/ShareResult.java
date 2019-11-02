package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 分享图
 *
 * @author daiysh
 * @date 2019-10-16 10:44
 **/
@Data
public class ShareResult {
	private int type;
	private String icon;
	private String text;
	public ShareResult(int type, String icon, String text) {
		this.type = type;
		this.icon = icon;
		this.text = text;
	}
}
