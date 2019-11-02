package com.junfeng.platform.payment.common.cache;

/**
 * 定义缓存名称,超时时长(秒),最大size
 * @author chenyx
 */
public enum CacheEnum {
	/**
	 * 支付结果查询缓存key定义
	 * <pre>
	 * key : key (pay_query_result_key)
	 * value :
	 * </pre>
	 */
    PAY_QUERY_SUCCESS_RESULT_KEY("pay_query_success_result_key", CacheConst.DEFAULT_MAXSIZE,CacheConst.PAY_QUERY_RESULT_TIMEOUT),
    REFUND_QUERY_SUCCESS_RESULT_KEY("refund_query_success_result_key", CacheConst.DEFAULT_MAXSIZE,CacheConst.REFUND_QUERY_RESULT_TIMEOUT),
    ;
    private String name = "";
	private Long maxSize= CacheConst.DEFAULT_MAXSIZE;    //最大數量
    private Long timeout= CacheConst.DEFAULT_TIMEOUT;    //过期时间（秒）


    private CacheEnum() {
    }
    private CacheEnum(String name) {
		this.name = name;
	}
	private CacheEnum(Long timeout) {
        this.timeout = timeout;
    }

    private CacheEnum(String name, Long maxSize) {
		this.maxSize = maxSize;
		this.name = name;
	}
	private CacheEnum(String name, Long maxSize, Long timeout) {
		this.maxSize = maxSize;
		this.timeout = timeout;
		this.name = name;
	}
	public Long getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
