package com.junfeng.platform.errorwarner;

import lombok.Data;

/**
 * @author fuh
 * @version 1.0 2019/10/22 11:40
 */
@Data
public class ErrorVo {
	/**
	 * 错误涉及的服务
	 * @author: fuh
	 * @createTime: 2019/10/22 15:29
	 */
	private String serviceName;
	/**
	 * 错误代码，邮件通知作为主题
	 * @author: fuh
	 * @createTime: 2019/10/22 15:25
	 */
	private String code;
	/**
	 * 错误描述，邮件通知作为正文
	 * @author: fuh
	 * @createTime: 2019/10/22 15:26
	 */
	private String message;
	/**
	 * 通知方式，目前支持邮件、钉钉
	 * @author: fuh
	 * @createTime: 2019/10/22 15:27
	 */
	private NotifyType notifyType;

	/**
	 * 邮件通知的接收邮箱
	 * @author: fuh
	 * @createTime: 2019/10/22 15:27
	 */
	private String sendMailTo;

	/**
	 * 钉钉消息类型
	 * @author: fuh
	 * @createTime: 2019/10/23 10:19
	 */
	private DingTalkMessageType dingTalkMessageType;

	/**
	 * ACTION_CARD类型的url，以及LINK的messageUrl
	 * @author: fuh
	 * @createTime: 2019/10/23 10:25
	 */
	private String url;

	/**
	 * LINK类型的pictureUrl
	 * @author: fuh
	 * @createTime: 2019/10/23 10:25
	 */
	private String pictureUrl;

	/**
	 * 自己指定机器人webhook
	 * @author: fuh
	 * @createTime: 2019/10/23 10:27
	 */
	private String webhook;

}
