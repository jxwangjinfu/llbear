package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 轮播图
 *
 * @author daiysh
 * @date 2019-10-16 10:44
 **/
@Data
public class CarouselResult {
	private String src;
	private String background;

	public CarouselResult(String src, String background) {
		this.src = src;
		this.background = background;
	}
}
