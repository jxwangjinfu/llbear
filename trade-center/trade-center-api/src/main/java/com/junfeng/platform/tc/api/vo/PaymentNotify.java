package com.junfeng.platform.tc.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-11 10:33
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class PaymentNotify {

	private String payOrderNo;
	private String mchOrderNo;
}
