package com.junfeng.platform.mc.api.feign.fallback;

import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.feign.RemoteMemberService;
import com.junfeng.platform.mc.api.result.MemberResult;
import com.junfeng.platform.mc.api.vo.MemberPointsFlowVo;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author daiysh
 * @date 2019/10/12
 * feign token  fallback
 */
@Slf4j
@Component
public class RemoteMemberServiceFallbackImpl implements RemoteMemberService {
	@Setter
	private Throwable cause;

	@Override
	public R<Boolean> register(MemberVo memberVo, String from) {
		log.error("feign 会员注册失败", cause);
		return R.feignFailed("会员创建失败");
	}

	@Override
	public R<Member> getMember(int userId, String from) {
		log.error("feign 查找会员失败", cause);
		return R.feignFailed("查找会员失败");
	}

	@Override
	public R<Boolean> updateMember(Member member, String from) {
		log.error("feign 更新会员失败", cause);
		return R.feignFailed("更新会员失败");
	}

	@Override
	public R<Boolean> addPointsflow(MemberPointsFlowVo memberPointsFlowVo, String fromIn) {
		log.error("feign 插入会员积分流水失败", cause);
		return R.feignFailed("插入会员积分流水失败");
	}
}
