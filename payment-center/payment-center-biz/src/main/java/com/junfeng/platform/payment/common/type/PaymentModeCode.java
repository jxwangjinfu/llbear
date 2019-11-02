package com.junfeng.platform.payment.common.type;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * 支付方式编码
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月19日 下午3:42:28
 * @version 1.0
 */
@Getter
public enum PaymentModeCode {
    WX_JSAPI("WX_JSAPI", "微信公众号"),
    WX_MINI("WX_MINI","微信小程序"),
    WX_BARCODE("WX_BARCODE", "微信条码"),
    ALIPAY_JSAPI("ALIPAY_JSAPI", "支付宝"),
    ALIPAY_BARCODE("ALIPAY_BARCODE", "支付宝条码"),
    UNIONPAY_BARCODE("UNIONPAY_BARCODE","银联条码"),
    DRAGONPAY_BARCODE("DRAGONPAY_BARCODE","龙支付条码");

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

	private PaymentModeCode(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static String getDescriptionByValue(String s) {
        for(PaymentModeCode obj : PaymentModeCode.values()) {
            if(StringUtils.equals(obj.getValue(),s)) {
                return obj.getDescription();
            }
        }
        return "";
    }
}
