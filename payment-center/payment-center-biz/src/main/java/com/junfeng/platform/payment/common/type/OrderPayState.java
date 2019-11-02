package com.junfeng.platform.payment.common.type;

/**
 * 订单支付状态
 *
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月17日 下午4:34:47
 * @version 1.0
 */
public enum OrderPayState {

    CREATE(0, "订单生成"), SUCCESS(1, "支付成功"), CLOSE(9, "已关闭"), ERROR(-1, "支付失败");

    private Integer value;
    private String description;

    private OrderPayState(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

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

}
