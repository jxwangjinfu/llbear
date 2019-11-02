package com.junfeng.platform.pc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.ApiConstant;
import com.junfeng.platform.pc.api.entity.*;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;
import com.junfeng.platform.pc.service.*;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内部调用
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/23 9:34
 * @projectName pig
 */
@Api(tags = "内部调用")
@RestController
@AllArgsConstructor
public class RemoteController {

	private final ProductService productService;
	private final SkuStockService skuStockService;
	private final SpuService spuService;
	private final CategoryService categoryService;
	private final BrandService brandService;
	private final CategorySpecificationTemplateService categorySpecificationTemplateService;

	@ApiOperation(value="查询商品基本信息", notes = "参数 spu")
	@GetMapping("/remote/page/product")
	@Inner
	public R<List<Spu>> getSpuPage(Page<Spu> page, Spu spu) {
		return R.ok(productService.getSpuPage(page,spu).getRecords());
	}

	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 类目表")
	@GetMapping("/remote/page/category")
	@Inner
	public R<List<Category>> getCategoryPage(Page<Category> page, Category category) {
		return R.ok(categoryService.getCategoryPage(page,category).getRecords());
	}

	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 品牌表")
	@GetMapping("/remote/page/brand")
	@Inner
	public R<List<Brand>> getBrandPage(Page<Brand> page, Brand brand) {
		return R.ok(brandService.getBrandPage(page,brand).getRecords());
	}

	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 类目关联模板")
	@GetMapping("/remote/page/categoryspecificationtemplate")
	@Inner
	public R<List<CategorySpecificationTemplate>> getCategorySpecificationTemplatePage(Page<CategorySpecificationTemplate> page,
																					   CategorySpecificationTemplate categorySpecificationTemplate) {
		return R.ok(categorySpecificationTemplateService.getCategorySpecificationTemplatePage(page,
			categorySpecificationTemplate).getRecords());
	}

	@ApiOperation(value="根据skuId查询商品相关信息", notes = "参数 skuId")
	@GetMapping("/remote/sku/{id}")
	@Inner
	public R getProductBySkuId(@PathVariable("id") Long skuId) {
		return R.ok(productService.getProductOnlySelfBySkuId(skuId));
	}

	@ApiOperation(value = "增加库存", notes = "参数:skuStockList")
	@SysLog("增加库存量")
	@PostMapping("/remote/addstock")
	@Inner
	public R addStock(@RequestBody List<SkuStock> skuStockList) {
		return R.ok(skuStockService.addSkuStock(skuStockList));
	}

	@ApiOperation(value = "减少库存", notes = "参数:skuStockList")
	@SysLog("减少库存量")
	@PostMapping("/remote/reducestock")
	@Inner
	public R reduceStock(@RequestBody List<SkuStock> skuStockList) {
		return R.ok(skuStockService.reduceSkuStock(skuStockList));
	}

	@ApiOperation(value = "增加商品销量",notes = "参数 SpuSaleCountVo")
	@SysLog("增加销量")
	@PostMapping("/remote/addsalecount")
	@Inner
	public R addSaleCount(@RequestBody SpuSaleCountVo spuSaleCountVo){
		return R.ok(spuService.addSaleCountBySpuId(spuSaleCountVo));
	}

	@Inner
	@PostMapping(ApiConstant.API_REMOTE_PRODUCT_INFO)
	public R<ProductDetailVo> getProductBySpuId(@RequestParam Long spuId){
		return R.ok(productService.getProductBySpuId(spuId));
	}
}
