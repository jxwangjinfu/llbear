package com.junfeng.platform.payment.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-09 17:06
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class PaymentPayVo {

    private String payOrderNo;
}
