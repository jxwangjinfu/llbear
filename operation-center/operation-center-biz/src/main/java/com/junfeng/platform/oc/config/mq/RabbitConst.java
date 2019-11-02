package com.junfeng.platform.oc.config.mq;

/**
 * Rabbit mq常量定义
 */
public class RabbitConst {

    /**
     * 优惠券生成消息队列
     */
    public final static String COUPON_GENERATE_NOTIFY_QUEUE = "coupon.generate.notify.queue";
    /**
     * 优惠券生成消息交换机
     */
    public final static String COUPON_GENERATE_NOTIFY_EXCHANGE = "coupon.generate.notify.exchange";
    /**
     * 优惠券生成路由关键字
     */
    public final static String COUPON_GENERATE_ROUTING_KEY_NOTIFY = "coupon.generate.routing.key.notify";

    /**
     * 红包生成消息队列
     */
    public final static String RED_ENVELOPE_GENERATE_NOTIFY_QUEUE = "red.envelope.generate.notify.queue";
    /**
     * 红包生成消息交换机
     */
    public final static String RED_ENVELOPE_GENERATE_NOTIFY_EXCHANGE = "red.envelope.generate.notify.exchange";
    /**
     * 红包生成路由关键字
     */
    public final static String RED_ENVELOPE_GENERATE_ROUTING_KEY_NOTIFY = "red.envelope.generate.routing.key.notify";

    /**
     * 礼品生成消息队列
     */
    public final static String GIFT_GENERATE_NOTIFY_QUEUE = "gift.generate.notify.queue";
    /**
     * 礼品生成消息交换机
     */
    public final static String GIFT_GENERATE_NOTIFY_EXCHANGE = "gift.generate.notify.exchange";
    /**
     * 礼品生成路由关键字
     */
    public final static String GIFT_GENERATE_ROUTING_KEY_NOTIFY = "gift.generate.routing.key.notify";

}
