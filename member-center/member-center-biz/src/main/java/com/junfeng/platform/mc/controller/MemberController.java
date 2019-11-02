package com.junfeng.platform.mc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.ApiConstant;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.junfeng.platform.mc.service.MemberPointsFlowService;
import com.junfeng.platform.mc.service.MemberService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 会员表
 *
 * @author daiysh
 * @date 2019-09-17 11:26:07
 */
@Api(tags = {"会员"})
@RestController
@AllArgsConstructor
public class MemberController {

	private final MemberService memberService;
	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param member 会员表
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 会员对象")
	@GetMapping(ApiConstant.API_MEMBER+"/page")
	public R<IPage<Member>> getMemberPage(Page<Member> page, Member member) {
		return R.ok(memberService.getMemberPage(page, member));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "单条记录查询", notes = "参数： 会员ID")
	@GetMapping(ApiConstant.API_MEMBER+"/{id}")
	public R<Member> getById(@PathVariable("id") Long id) {
		return R.ok(memberService.getById(id));
	}



	/**
	 * 新增记录
	 *
	 * @param member
	 * @return R
	 */
	@ApiOperation(value = "新增", notes = "参数： 会员对象")
	@SysLog("新增会员表")
	@PostMapping(ApiConstant.API_MEMBER)
	@PreAuthorize("@pms.hasPermission('mc_member_add')")
	public R<Boolean> save(@RequestBody Member member) {
		return R.ok(memberService.save(member));
	}



	/**
	 * 修改记录
	 *
	 * @param member
	 * @return R
	 */
	@ApiOperation(value = "更新会员", notes = "参数： 会员对象")
	@SysLog("修改会员表")
	@PutMapping(ApiConstant.API_MEMBER)
	@PreAuthorize("@pms.hasPermission('mc_member_edit')")
	public R update(@RequestBody Member member) {
		return R.ok(memberService.updateById(member));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除会员", notes = "参数： 会员id")
	@SysLog("删除会员表")
	@DeleteMapping(ApiConstant.API_MEMBER+"/{id}")
	@PreAuthorize("@pms.hasPermission('mc_member_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(memberService.removeById(id));
	}

	/**
	 * 更新会员
	 *
	 * @param member
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-30  10:09
	 **/
	@ApiOperation(value = "更新会员", notes = "参数： 会员对象")
	@Inner
	@PostMapping(ApiConstant.API_MEMBER_REMOTE_UPDATE)
	public R<Boolean> updateMember(@RequestBody Member member) {
		Member dbMember = memberService.getById(member.getId());
		if (dbMember == null) {
			return R.failed("不存在会员");
		}

		return R.ok(memberService.updateById(member));
	}

	/**
	 * 通过userId查询会员信息
	 *
	 * @param userId
	 * @return R
	 */
	@ApiOperation(value = "查询会员信息", notes = "参数： userId")
	@Inner
	@GetMapping(ApiConstant.API_MEMBER_REMOTE_GET+"/{userId}")
	public R<Member> getMember(@PathVariable("userId") Long userId) {
		Member dbMember = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUserId, userId));
		if (dbMember == null) {
			return R.failed("不存在会员信息");
		}

		return R.ok(dbMember);
	}

	/**
	 * 注册会员
	 *
	 * @param memberVo
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-30  10:50
	 **/
	@ApiOperation(value = "注册会员", notes = "参数： memberVo对象")
	@Inner
	@PostMapping(ApiConstant.API_MEMBER_REMOTE_REGISTER)
	public R<Boolean> register(@RequestBody MemberVo memberVo) {
		return memberService.handleRegister(memberVo);
	}

}
