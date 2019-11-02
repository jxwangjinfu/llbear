package com.junfeng.platform.oc.entity;

import lombok.Data;

/**
 * 会员红包DTO
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 16:21
 * @projectName pig
 */
@Data
public class RedEnvelopeMemberDTO {

	private Long id;
	private Long mcMemberId;
	private Long ocRedEnvelopeId;
	private Integer money;
	private String redEnvelopeName;
	private String clientId;
}
