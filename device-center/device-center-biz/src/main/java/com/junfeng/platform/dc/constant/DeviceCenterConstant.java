package com.junfeng.platform.dc.constant;

/**
 * @Author: hanyx
 * @CreateTime: 2019-09-26 16:20:35
 * @Description: 常量
 */
public interface DeviceCenterConstant {

	/**
	 * 服务名称
	 */
	String SERVICE_NAME = "device-center";
	/**
	 * 设备回调默认路径
	 */
	String DEVICE_CALLBACK_JOB_URL = "http://device-center/device/callback/state";

	/**
	 * 0 0/5 * * * ?    cron表达式，表示每5分钟执行任务
	 */
	String PERIOD_TIME_RULE = "0 0/5 * * * ? ";
	/**
	 * 设备lastOnlineTime与当前时间相差大于PERIOD_TIME（毫秒），则视设备为离线状态
	 */
	int PERIOD_TIME = 5 * 60 * 1000;

	/**
	 * 设备在线
	 */
	String STATE_ONLINE = "1";

	/**
	 * 设备离线
	 */
	String STATE_OFFLINE = "0";

	/**
	 * 版本未发布
	 */
	int STATE_UNPUBLISHED = 0;
	/**
	 * 版本已发布
	 */
	int STATE_PUBLISHED = 1;

	/**
	 * 删除标志（已删除）
	 */
	String DEL_FLAG_TRUE = "1";
	/**
	 * 删除标志（未删除）
	 */
	String DEL_FLAG_FALSE = "0";

	/**
	 * 默认值-1
	 */
	int DEFAULT_LONG = -1;

	/**
	 * 国家
	 */
	int AREA_TYPE_COUNTRY = 1;
	/**
	 * 省份、直辖市
	 */
	int AREA_TYPE_PROVINCE = 2;
	/**
	 * 地市
	 */
	int AREA_TYPE_CITY = 3;
	/**
	 * 区县
	 */
	int AREA_TYPE_DISTRICT = 4;
}
