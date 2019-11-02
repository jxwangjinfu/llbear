package com.junfeng.platform.oc.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/21 15:08
 * @projectName pig
 */
@Data
@EqualsAndHashCode
public class SignInVO extends CalculatePointsVO {
	//次数
	private Integer count;
}
