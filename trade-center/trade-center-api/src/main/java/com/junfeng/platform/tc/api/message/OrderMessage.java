package com.junfeng.platform.tc.api.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-25 15:22
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class OrderMessage {
	private String tradeOrderNo;
	private Long userId;
	private Long memberId;
}
