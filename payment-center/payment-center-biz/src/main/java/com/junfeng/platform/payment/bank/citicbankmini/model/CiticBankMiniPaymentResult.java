package com.junfeng.platform.payment.bank.citicbankmini.model;

import com.junfeng.platform.payment.bank.citicbankmini.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias(value = "xml", impl = CiticBankMiniPaymentResult.class)
public class CiticBankMiniPaymentResult extends BaseBean {

    @XStreamAlias("pay_info")
    private String payInfo;

}
