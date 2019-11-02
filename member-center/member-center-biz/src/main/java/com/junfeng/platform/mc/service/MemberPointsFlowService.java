package com.junfeng.platform.mc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.mc.api.entity.MemberPointsFlow;

/**
 * 会员积分流水表
 *
 * @author daiysh
 * @date 2019-10-08 15:03:36
 */
public interface MemberPointsFlowService extends IService<MemberPointsFlow> {

	/**
	 * 会员积分流水表简单分页查询
	 *
	 * @param memberPointsFlow 会员积分流水表
	 * @return
	 */
	IPage<MemberPointsFlow> getMemberPointsFlowPage(Page<MemberPointsFlow> page, MemberPointsFlow memberPointsFlow);

	/**
	 * 处理会员积分流水
	 *
	 * @param memberPointsFlow : 会员积分流水
	 * @return: java.lang.Boolean
	 * @author: daiysh
	 * @createTime: 2019-10-08  15:11
	 **/
	Boolean handlePointsFlow(MemberPointsFlow memberPointsFlow);
}
