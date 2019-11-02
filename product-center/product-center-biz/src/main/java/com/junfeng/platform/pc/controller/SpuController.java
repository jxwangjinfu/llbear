package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;
import com.junfeng.platform.pc.service.SpuService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 商品信息
 *
 * @author lw
 * @date 2019-10-18 15:43:57
 */
@Api(tags = {"商品信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/spu")
public class SpuController {

	private final SpuService spuService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param spu  商品信息
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 商品信息")
	@GetMapping("/page")
	public R<IPage<Spu>> getSpuPage(Page<Spu> page, Spu spu) {
		return R.ok(spuService.getSpuPage(page, spu));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Spu> getById(@PathVariable("id") Long id) {
		return R.ok(spuService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param spu
	 * @return R
	 */
	@ApiOperation(value = "新增商品信息", notes = "参数： spu")
	@SysLog("新增商品信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('pc_spu_add')")
	public R save(@RequestBody Spu spu) {
		return R.ok(spuService.save(spu));
	}

	/**
	 * 修改记录
	 *
	 * @param spu
	 * @return R
	 */
	@ApiOperation(value = "修改商品信息", notes = "参数： spu")
	@SysLog("修改商品信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('pc_spu_edit')")
	public R update(@RequestBody Spu spu) {
		return R.ok(spuService.updateById(spu));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除商品信息", notes = "参数： id")
	@SysLog("删除商品信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('pc_spu_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(spuService.removeById(id));
	}

	/**
	 * 根据spuid 增加销售数量
	 * @author: lw
	 * @createTime: 2019/10/21 9:41
	 * @param spuSaleCountVo
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@ApiOperation(value = "增加商品销量",notes = "参数 SpuSaleCountVo")
	@SysLog("增加销量")
	@PostMapping("/addsalecount")
	public R addSaleCount(@RequestBody SpuSaleCountVo spuSaleCountVo){
		return R.ok(spuService.addSaleCountBySpuId(spuSaleCountVo));
	}
}
