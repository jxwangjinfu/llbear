package com.junfeng.platform.tc.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.tc.api.ApiConstant;
import com.junfeng.platform.tc.api.entity.GroupOrder;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.vo.GroupOrderRequest;
import com.junfeng.platform.tc.service.GroupOrderService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 团购订单
 *
 * @author wangk
 * @date 2019-10-22 16:17:48
 */
@Api(tags = {"团购订单"})
@RestController
@AllArgsConstructor
@RequestMapping("/grouporder")
public class GroupOrderController {

    private final GroupOrderService groupOrderService;

	@ApiOperation(value = "团购订单创建", notes = "")
	@PostMapping(ApiConstant.API_ORDER_CONSUMER_GROUP_CREATE)
	public R<Order> createGroupOrder(@RequestBody GroupOrderRequest request) {

		Order order = groupOrderService.leaderCreateGroupOrder(request);



		return R.ok(order);
	}

	@ApiOperation(value = "团购订单加入", notes = "")
	@PostMapping(ApiConstant.API_ORDER_CONSUMER_GROUP_JOIN)
	public R<Order> joinGroupOrder(@RequestBody GroupOrderRequest request) {

		Assert.isTrue(StrUtil.isNotBlank(request.getGroupOrderNo()), "团购ID不存在，无法加入");

		Order order = groupOrderService.memberJoinGroupOrder(request);

		return R.ok(order);
	}
    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param groupOrder
     *            团购订单
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 团购订单")
    @GetMapping("/page")
    public R<IPage<GroupOrder>> getGroupOrderPage(Page<GroupOrder> page, GroupOrder groupOrder) {
        return R.ok(groupOrderService.page(page,
                Wrappers.<GroupOrder> lambdaQuery()
//					.eq(GroupOrder::getRole, GroupOrderConstant.GROUP_LEADER)
					.eq(StrUtil.isNotBlank(groupOrder.getTradeOrderNo()), GroupOrder::getTradeOrderNo, groupOrder.getTradeOrderNo())
			)
		);
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<GroupOrder> getById(@PathVariable("id") Long id) {
        return R.ok(groupOrderService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param groupOrder
     * @return R
     */
    @ApiOperation(value = "新增团购订单", notes = "参数： groupOrder")
    @SysLog("新增团购订单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('tc_grouporder_add')")
    public R save(@RequestBody GroupOrder groupOrder) {
        return R.ok(groupOrderService.save(groupOrder));
    }

    /**
     * 修改记录
     *
     * @param groupOrder
     * @return R
     */
    @ApiOperation(value = "修改团购订单", notes = "参数： groupOrder")
    @SysLog("修改团购订单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('tc_grouporder_edit')")
    public R update(@RequestBody GroupOrder groupOrder) {
        return R.ok(groupOrderService.updateById(groupOrder));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除团购订单", notes = "参数： id")
    @SysLog("删除团购订单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('tc_grouporder_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(groupOrderService.removeById(id));
    }

}
