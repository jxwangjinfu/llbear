package com.junfeng.platform.tc.api.order.state;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-28 14:24
 * @projectName pig
 */
public interface GroupOrderConstant {

	//是否为团长 0表示团长 1表示团员
	String GROUP_LEADER = "leader";
	String GROUP_MEMBER = "member";

	//团单状态 0表示成功 1等待支付
	String GROUP_STATE_SUCCESS = "0";
	String GROUP_STATE_WAIT_FOR_PAY = "1";
	String GROUP_STATE_PROCESSING = "2";
	String GROUP_STATE_FAIL = "9";
}
