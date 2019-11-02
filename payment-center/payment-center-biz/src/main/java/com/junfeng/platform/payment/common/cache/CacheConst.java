package com.junfeng.platform.payment.common.cache;

/**
 * redis key常量定义类
 * @author hujie
 *
 */
public class CacheConst {

	// 每个缓存最大量控制,针对本地缓存配置
	public static final Long DEFAULT_MAXSIZE = 50000L;
    /**
     * 缓存过期时间,默认永久存在  单位:秒
     * <pre>
     * 	注  : 该默认值不可更改.
     * </pre>
     * redisson提供的spring Cache :
     * 存在过期时间对象是 RMapCache
     * 不过期对象 RMap
     * 两个对象不可相互转换,不然做值存取时会报错
     */
	public static final Long DEFAULT_TIMEOUT = 0L;
	/**
	 * 支付结果缓存时间为:30秒
	 */
	public static final Long PAY_QUERY_RESULT_TIMEOUT = 30L;
	/**
     * 退款结果缓存时间为:30秒
     */
    public static final Long REFUND_QUERY_RESULT_TIMEOUT = 30L;

	/**
	 * 获取缓存key
	 * @param prefix
	 * @param key
	 * @return
	 */
	public static String getKey(String prefix, String key) {
		StringBuffer buffer = new StringBuffer(prefix);
		buffer.append("-");
		buffer.append(key);
		return buffer.toString();
	}
}
