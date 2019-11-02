package com.junfeng.platform.errorwarner.sender;

import com.junfeng.platform.errorwarner.ErrorVo;

/**
 * 对错误进行告警
 * @author fuh
 * @version 1.0 2019/10/22 15:20
 */
public interface ErrorWarnerSender {

	/**
	 * 功能描述: 对错误进行告警
	 * @author: fuh
	 * @createTime: 2019/10/22 15:22
	 * @param errorVo
	 * @return void
	 */
	void send(ErrorVo errorVo);

}
