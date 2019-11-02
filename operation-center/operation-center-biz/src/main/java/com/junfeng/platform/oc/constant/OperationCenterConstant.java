package com.junfeng.platform.oc.constant;

/**
 * 常量定义
 *
 * @projectName:operation-center-biz
 * @author:Wangjf
 * @date:2019年9月23日 上午10:22:14
 * @version 1.0
 */
public interface OperationCenterConstant {

    /**
     * 服务名称
     */
    String SERVICE_NAME = "operation-center";
    /**
     * 优惠券回调默认路径
     */
    String COUPON_CALLBACK_JOB_URL = "http://operation-center/couponquart/callback/";

    /**
     * 红包回调默认路径
     */
    String RED_ENVELOPE_CALLBACK_JOB_URL = "http://operation-center/redenvelopequart/callback/";

    /**
     * 定时任务默认回调路径
     */
    String QUARTZ_CALLBACK_URL = "http://operation-center/quartzlog/callback/";
    /**
     * 每批生成大小
     */
    int COUPON_GENERATE_PART_SIZE = 1000;

}
