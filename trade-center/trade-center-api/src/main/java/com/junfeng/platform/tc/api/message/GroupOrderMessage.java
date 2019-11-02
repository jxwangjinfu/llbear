package com.junfeng.platform.tc.api.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-28 11:38
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class GroupOrderMessage {
	private String groupOrderNo;
}
