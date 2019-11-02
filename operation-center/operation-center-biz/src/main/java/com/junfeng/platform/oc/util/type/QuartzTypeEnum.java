package com.junfeng.platform.oc.util.type;

/**
 * QuartzType枚举
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
public enum QuartzTypeEnum {

    COUPON(1, "优惠券"), REDENVELOPE(2, "红包"), GIFT(3, "赠品"), BUY(4, "买赠"), GROUPBUY(5, "团购活动");

    private Integer code;
    private String msg;

    private QuartzTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (QuartzTypeEnum type : QuartzTypeEnum.values()) {
            if (type.getCode() == code) {
                return type.msg;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
