package com.junfeng.platform.mc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.junfeng.platform.mc.service.MemberPointsFlowService;
import com.junfeng.platform.mc.service.MemberService;
import com.junfeng.platform.mc.mapper.MemberMapper;
import com.junfeng.platform.oc.api.feign.OcRemoteService;
import com.junfeng.platform.oc.api.vo.PointsVO;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员表
 *
 * @author daiysh
 * @date 2019-09-23 09:22:11
 */
@Service("memberService")
@AllArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

	private final RemoteUserService remoteUserService;

	private final OcRemoteService ocRemoteService;
	/**
	 * 会员表简单分页查询
	 *
	 * @param member 会员表
	 * @return
	 */
	@Override
	public IPage<Member> getMemberPage(Page<Member> page, Member member) {
		return baseMapper.getMemberPage(page, member);
	}

	@Override
	public R<Boolean> handleRegister(MemberVo memberVo) {
		//1 创建系统用户
		R<Integer> userR = createSysUser(memberVo.getUserName(),memberVo.getPassword());

		//2 参数校验
		if (userR != null && userR.getCode() != 0) {
			return R.failed(userR.getMsg());
		}

		//3 创建会员信息
		Integer userId = userR.getData();
		Member member = new Member();
		member.setNickName(memberVo.getNickName());
		member.setUserId(userId);
		member.setScope(memberVo.getClientId());

		try {
			super.save(member);
		} catch (Exception e) {
			// TODO:回滚sysUser表中的数据
			return R.failed("创建会员失败");
		}
		//4 通知运营中心，积分计算
		notifyOperationCenter(memberVo.getClientId(),member.getId());
		//5 给用户分配默认角色
		remoteUserService.addUserRole(userId,SecurityConstants.FROM_IN);

		return R.ok();

	}
	private void notifyOperationCenter(String clientId,long memberId) {
		PointsVO pointsVO = new PointsVO();
		pointsVO.setClientId(clientId);
		pointsVO.setMemberId(memberId);

		ocRemoteService.memberPointCalculate(pointsVO, SecurityConstants.FROM_IN);
	}

	private R<Integer> createSysUser(String userName,String password) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(userName);
		sysUser.setPassword(password);
		sysUser.setUsertype("member");

		return remoteUserService.createUser(sysUser, SecurityConstants.FROM_IN);
	}



}
