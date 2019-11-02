package com.junfeng.platform.pc.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 描述
 *
 * @author daiysh
 * @date 2019-10-29 17:54
 **/
@Data
@Accessors(chain = true)
public class SpecialVo {
	private String groupName;
	private List<String> specification;
}
