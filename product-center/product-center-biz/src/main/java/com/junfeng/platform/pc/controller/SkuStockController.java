package com.junfeng.platform.pc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.SkuStock;
import com.junfeng.platform.pc.service.SkuStockService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sku库存表
 *
 * @author lw
 * @date 2019-10-14 15:41:03
 */
@Api(tags = {"sku库存表"})
@RestController
@AllArgsConstructor
@RequestMapping("/skustock")
public class SkuStockController {

	private final SkuStockService skuStockService;

	/**
	 * 简单分页查询
	 *
	 * @param page     分页对象
	 * @param skuStock sku库存表
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 sku库存表")
	@GetMapping("/page")
	public R<IPage<SkuStock>> getSkuStockPage(Page<SkuStock> page, SkuStock skuStock) {
		return R.ok(skuStockService.getSkuStockPage(page, skuStock));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param skuCode
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： skuCode")
	@GetMapping("/{skuCode}")
	public R<SkuStock> getById(@PathVariable("skuCode") Long skuCode) {
		return R.ok(skuStockService.getById(skuCode));
	}

	/**
	 * 新增记录
	 *
	 * @param skuStock
	 * @return R
	 */
	@ApiOperation(value = "新增sku库存表", notes = "参数： skuStock")
	@SysLog("新增sku库存表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('pc_skustock_add')")
	public R save(@RequestBody SkuStock skuStock) {
		return R.ok(skuStockService.save(skuStock));
	}

	/**
	 * 修改记录
	 *
	 * @param skuStock
	 * @return R
	 */
	@ApiOperation(value = "修改sku库存表", notes = "参数： skuStock")
	@SysLog("修改sku库存表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('pc_skustock_edit')")
	public R update(@RequestBody SkuStock skuStock) {
		return R.ok(skuStockService.updateById(skuStock));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param skuCode
	 * @return R
	 */
	@ApiOperation(value = "删除sku库存表", notes = "参数： skuCode")
	@SysLog("删除sku库存表")
	@DeleteMapping("/{skuCode}")
	@PreAuthorize("@pms.hasPermission('pc_skustock_del')")
	public R removeById(@PathVariable Long skuCode) {
		return R.ok(skuStockService.removeById(skuCode));
	}

	@ApiOperation(value = "增加库存", notes = "参数:skuStockList")
	@SysLog("增加库存量")
	@PostMapping("/addstock")
	public R addStock(@RequestBody List<SkuStock> skuStockList) {
		return R.ok(skuStockService.addSkuStock(skuStockList));
	}

	@ApiOperation(value = "减少库存", notes = "参数:skuStockList")
	@SysLog("减少库存量")
	@PostMapping("/reducestock")
	public R reduceStock(@RequestBody List<SkuStock> skuStockList) {
		return R.ok(skuStockService.reduceSkuStock(skuStockList));
	}
}
