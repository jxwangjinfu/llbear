package com.junfeng.platform.mc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.pig4cloud.pig.common.core.util.R;

/**
 * 会员表
 *
 * @author daiysh
 * @date 2019-09-23 09:22:11
 */
public interface MemberService extends IService<Member> {

	/**
	 * 会员表简单分页查询
	 *
	 * @param member 会员表
	 * @return
	 */
	IPage<Member> getMemberPage(Page<Member> page, Member member);

	/**
	 * 处理注册流程
	 *
	 * @param memberVo
	 * @return: Member
	 * @author: daiysh
	 * @createTime: 2019-09-23  10:49
	 **/
	R<Boolean> handleRegister(MemberVo memberVo);

}
