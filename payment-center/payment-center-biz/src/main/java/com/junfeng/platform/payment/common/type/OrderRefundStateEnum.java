package com.junfeng.platform.payment.common.type;

/**
 * 银联退款订单状态
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 上午10:46:16
 * @version 1.0
 */
public enum OrderRefundStateEnum {

    CREATE(0, "订单生成"),

    SUCCESS(2, "退款成功"),

    FAIL(3, "退款失败"),

    COMPLETE(4, "业务处理完成");

    private Integer value;
    private String description;



    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private OrderRefundStateEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

}
