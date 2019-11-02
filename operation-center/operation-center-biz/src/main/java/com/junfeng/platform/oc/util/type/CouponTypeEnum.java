package com.junfeng.platform.oc.util.type;

/**
 * 优惠券类型
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 15:31
 * @projectName pig
 */
public enum CouponTypeEnum {

    DISCOUNT(2, "折扣券"), FULLMINUS(1, "满减券"), RANDOM(3, "随机券");

    private Integer code;
    private String msg;

    private CouponTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (CouponTypeEnum type : CouponTypeEnum.values()) {
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
