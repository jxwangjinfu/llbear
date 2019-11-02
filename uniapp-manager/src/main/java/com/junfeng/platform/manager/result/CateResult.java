package com.junfeng.platform.manager.result;

import com.junfeng.platform.pc.api.entity.Category;
import lombok.Data;

/**
 * 类别列表
 *
 * @author daiysh
 * @date 2019-10-25 15:17
 **/
@Data
public class CateResult {
	private Long id;
	private Long pid;
	private String name;
	private String picture;

	public CateResult() {}
	public CateResult(long id,long pid,String name,String picture) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.picture = picture;
	}

	public static CateResult transfer(Category category) {
		CateResult cateResult = new CateResult();
		if (category == null) {
			return cateResult;
		}
 		cateResult.setId(category.getId());
		cateResult.setPid(category.getParentId());
		cateResult.setName(category.getName());

		return cateResult;
	}
}
