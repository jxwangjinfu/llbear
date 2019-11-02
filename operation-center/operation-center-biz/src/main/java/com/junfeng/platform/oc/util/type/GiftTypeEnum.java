package com.junfeng.platform.oc.util.type;

/**
 * 赠品类型
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 15:31
 * @projectName pig
 */
public enum GiftTypeEnum {
    REDENVELOPE(2, "红包"), COUPON(1, "优惠券"), GIFT(3, "赠品");

    private Integer code;
    private String msg;

    private GiftTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (GiftTypeEnum type : GiftTypeEnum.values()) {
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
