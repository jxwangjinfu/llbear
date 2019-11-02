package com.junfeng.platform.payment.config.mq;
/**
 * 消息队列常量定义类
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月13日 下午5:18:05
 * @version 1.0
 */
public class RabbitConst {
	/**
	 * 通知回调队列
	 */
	public final static String NOTIFY_QUEUE = "notify.queue";
	/**
	 * 延迟重试通知队列
	 */
	public final static String DELAY_NOTIFY_QUEUE = "delay.notify.queue";
	/**
	 * 延迟重试通知交换机
	 */
	public final static String DELAY_NOTIFY_EXCHANGE = "delay.notify.exchange";
	/**
	 * 通知回调交换机
	 */
	public final static String NOTIFY_EXCHANGE = "notify.exchange";
	/**
	 * 路由关键字:迟延通知回调路由标志
	 */
	public final static String ROUTING_KEY_DELAY_NOTIFY = "routing.key.delay.notify";
	/**
	 * 路由关键字:通知回调处理标志
	 */
	public final static String ROUTING_KEY_NOTIFY = "routing.key.notify";
	/**
	 * 消息类型
	 */
	public final static String MESSAGE_PROPERTIES_CONTENTTYPE = "application/json";
}
