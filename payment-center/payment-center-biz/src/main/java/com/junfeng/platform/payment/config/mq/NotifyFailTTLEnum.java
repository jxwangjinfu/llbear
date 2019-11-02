package com.junfeng.platform.payment.config.mq;

import lombok.Getter;

@Getter
public enum NotifyFailTTLEnum {

    FAIL_COUNT_1("15000",1),
    FAIL_COUNT_2("15000",2),
    FAIL_COUNT_3("30000",3),
    FAIL_COUNT_4("180000",4),
    FAIL_COUNT_5("1800000",5),
    FAIL_COUNT_6("1800000",6),
    FAIL_COUNT_7("1800000",7),
    FAIL_COUNT_8("3600000",8),
    FAIL_COUNT_LIMIT("0",9),
    FAIL_NOTIFY_TTL("1000",0),
    ;
    /**
     * 延迟时间,单位：毫秒
     */
    private String ttl;
    /**
     * 失败次数
     */
    private Integer failCount;
    private NotifyFailTTLEnum(String ttl, Integer failCount) {
        this.ttl = ttl;
        this.failCount = failCount;
    }


}
