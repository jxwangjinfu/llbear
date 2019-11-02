package com.junfeng.platform.mc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.entity.MemberPointsFlow;
import com.junfeng.platform.mc.api.vo.MemberPointsFlowVo;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.mc.service.MemberPointsFlowService;
import com.pig4cloud.pig.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员积分流水表
 *
 * @author daiysh
 * @date 2019-10-08 15:03:36
 */
@Api(tags = {"会员积分流水表"})
@RestController
@AllArgsConstructor
@RequestMapping("/memberpointsflow")
public class MemberPointsFlowController {

	private final MemberPointsFlowService memberPointsFlowService;

	/**
	 * 简单分页查询
	 *
	 * @param page             分页对象
	 * @param memberPointsFlow 会员积分流水表
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 会员积分流水表")
	@GetMapping("/page")
	public R<IPage<MemberPointsFlow>> getMemberPointsFlowPage(Page<MemberPointsFlow> page, MemberPointsFlow memberPointsFlow) {
		return R.ok(memberPointsFlowService.getMemberPointsFlowPage(page, memberPointsFlow));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<MemberPointsFlow> getById(@PathVariable("id") Long id) {
		return R.ok(memberPointsFlowService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param memberPointsFlowVo
	 * @return R
	 */
	@Inner
	@PostMapping("/remote/add")
	public R<Boolean> saveRecord(@RequestBody MemberPointsFlowVo memberPointsFlowVo) {
		// 转换
		MemberPointsFlow memberPointsFlow = new MemberPointsFlow();
		BeanUtil.copyProperties(memberPointsFlowVo, memberPointsFlow, CopyOptions.create().setIgnoreNullValue(true));
		return R.ok(memberPointsFlowService.handlePointsFlow(memberPointsFlow));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除会员积分流水表", notes = "参数： id")
	@SysLog("删除会员积分流水表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('mc_memberpointsflow_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(memberPointsFlowService.removeById(id));
	}

}
