package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 模型
 *
 * @author daiysh
 * @date 2019-10-29 10:42
 **/
@Data
public class SkuModel {
	private int id;
	private int pid;
	private String name;

	public SkuModel(int id,int pid,String name) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
}
