package com.junfeng.platform.pc.controller;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.ProductCreateVo;
import com.junfeng.platform.pc.service.ProductService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 产品表
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/15 10:07
 * @projectName pig
 */
@Api(tags = "产品业务")
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@ApiOperation(value = "新增产品", notes = "参数： productVo ")
	@SysLog("新增产品")
	@PostMapping
	public R createProduct(@RequestBody ProductCreateVo productVo){
		System.out.println("productVo = " + productVo);
		return R.ok(productService.add(productVo));
	}

	@ApiOperation(value = "修改产品", notes = "参数： productVo ")
	@SysLog("修改产品")
	@PutMapping
	public R updateProduct(@RequestBody ProductCreateVo productVo){
		return R.ok(productService.update(productVo));
	}

	@ApiOperation(value = "删除产品", notes = "参数： id")
	@SysLog("删除产品")
	@DeleteMapping("/{id}")
	public R deleteProduct(@PathVariable Long id){
		return R.ok(productService.delete(id));
	}

	@ApiOperation(value = "上架产品", notes = "参数： id")
	@SysLog("上架产品")
	@PutMapping("/on/{id}")
	public R onProduct(@PathVariable Long id){
		return R.ok(productService.onProduct(id));
	}

	@ApiOperation(value = "上架产品", notes = "参数： id")
	@SysLog("下架产品")
	@PutMapping("/off/{id}")
	public R offProduct(@PathVariable Long id){
		return R.ok(productService.offProduct(id));
	}

	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 Spu查询组")
	@GetMapping("/page")
	public R<IPage<Spu>> getSpuPage(Page<Spu> page, Spu spu) {
		return R.ok(productService.getSpuPage(page,spu));
	}

	@ApiOperation(value="根据skuId查询商品相关信息", notes = "参数 id")
	@GetMapping("/sku/{id}")
	public R getProductBySkuId(@PathVariable("id") Long skuId) {
		return R.ok(productService.getProductOnlySelfBySkuId(skuId));
	}
}
