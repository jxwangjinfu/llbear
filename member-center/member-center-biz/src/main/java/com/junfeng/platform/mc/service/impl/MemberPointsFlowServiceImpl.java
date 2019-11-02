package com.junfeng.platform.mc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.entity.MemberPointsFlow;
import com.junfeng.platform.mc.enums.OptTypeEnum;
import com.junfeng.platform.mc.service.MemberPointsFlowService;
import com.junfeng.platform.mc.mapper.MemberPointsFlowMapper;
import com.junfeng.platform.mc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分流水表
 *
 * @author daiysh
 * @date 2019-10-08 15:03:36
 */
@Service("memberPointsFlowService")
public class MemberPointsFlowServiceImpl extends ServiceImpl<MemberPointsFlowMapper, MemberPointsFlow> implements MemberPointsFlowService {

	@Autowired
	private MemberService memberService;

	/**
	 * 会员积分流水表简单分页查询
	 *
	 * @param memberPointsFlow 会员积分流水表
	 * @return
	 */
	@Override
	public IPage<MemberPointsFlow> getMemberPointsFlowPage(Page<MemberPointsFlow> page, MemberPointsFlow memberPointsFlow) {
		return baseMapper.getMemberPointsFlowPage(page, memberPointsFlow);
	}

	@Override
	public Boolean handlePointsFlow(MemberPointsFlow memberPointsFlow) {
		Integer points = memberPointsFlow.getPoints();
		if (points == null) {
			points = 0;
		}
		String operationType = memberPointsFlow.getPoints() > 0 ? OptTypeEnum.ADD.getValue() : OptTypeEnum.SUB.getValue();

		Member member = memberService.getById(memberPointsFlow.getMemberid());
		int beforePoints = member.getPoints();
		int afterPoints = beforePoints + points;

		memberPointsFlow.setPoints(points);
		memberPointsFlow.setOperationType(operationType);
		memberPointsFlow.setBeforepoints(beforePoints);
		memberPointsFlow.setAfterpoints(afterPoints);

		save(memberPointsFlow);

		member.setPoints(afterPoints);

		memberService.updateById(member);
		return true;
	}

}
