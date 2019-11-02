package com.junfeng.platform.mc.api.feign;

import com.junfeng.platform.mc.api.ApiConstant;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.feign.factory.RemoteMemberServiceFallbackFactory;
import com.junfeng.platform.mc.api.vo.MemberPointsFlowVo;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author daiysh
 * @date 2019-10-08 15:25
 **/
@FeignClient(contextId = "remoteMemberService", value = ServiceNameConstants.MEMBER_CENTER_SERVICE, fallbackFactory = RemoteMemberServiceFallbackFactory.class)
public interface RemoteMemberService {

	/**
	 * 注册会员
	 *
	 * @param memberVo
	 * @param from
	 * @return: com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.mc.api.entity.Member>
	 * @author: daiysh
	 * @createTime: 2019-10-30  09:47
	 **/
	@PostMapping(ApiConstant.API_MEMBER_REMOTE_REGISTER)
	R<Boolean> register(@RequestBody MemberVo memberVo, @RequestHeader(SecurityConstants.FROM) String from);
    /**
     * 获取会员信息
     *
     * @param userId
     * @param from
     * @return: com.junfeng.platform.mc.api.entity.Member
     * @author: daiysh
     * @createTime: 2019-10-14 09:42
     **/
    @GetMapping(ApiConstant.API_MEMBER_REMOTE_GET+"/{userId}")
    R<Member> getMember(@PathVariable("userId") int userId, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 更新会员信息
	 *
	 * @param member
	 * @param from
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-17 10:05
	 **/
	@PostMapping(ApiConstant.API_MEMBER_REMOTE_UPDATE)
	R<Boolean> updateMember(@RequestBody Member member, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 添加会员积分流水
	 *
	 * @param memberPointsFlowVo
	 * @param from
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-08  15:30
	 **/
	@PostMapping(ApiConstant.API_MEMBERPOINTSFLOW_REMOTE_ADD)
	R<Boolean> addPointsflow(@RequestBody MemberPointsFlowVo memberPointsFlowVo, @RequestHeader(SecurityConstants.FROM) String from);

}
