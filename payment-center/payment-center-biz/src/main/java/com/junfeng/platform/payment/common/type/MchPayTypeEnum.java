package com.junfeng.platform.payment.common.type;

/**
 * 商户支付类型枚举
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月1日 下午6:42:50
 * @version 1.0
 */
public enum MchPayTypeEnum {

    MINI_PROGRAMME("miniProgramme","小程序支付"),POS_PAY("posPay","pos机支付");

    private String value;
    private String description;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param value
     * @param description
     */
    private MchPayTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }



}
