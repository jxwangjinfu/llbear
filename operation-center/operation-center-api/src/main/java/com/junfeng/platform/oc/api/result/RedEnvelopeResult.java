package com.junfeng.platform.oc.api.result;

import lombok.Data;

/**
 * 红包结果
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 17:26
 * @projectName pig
 */
@Data
public class RedEnvelopeResult {
	private Long id;
	private Long mcMemberId;
	private Long ocRedEnvelopeId;
	private Integer money;
	private String redEnvelopeName;
	private String clientId;
	//折扣 单位：分
	private Integer discount;
}
