package com.junfeng.platform.pc.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.ApiConstant;
import com.junfeng.platform.pc.api.entity.*;
import com.junfeng.platform.pc.api.feign.factory.RemoteProductServiceFallbackFactory;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/17 17:58
 * @projectName pig
 */
@FeignClient(contextId = "remoteProductService" ,value = ServiceNameConstants.PRODUCT_CENTER_SERVICE,fallbackFactory
	= RemoteProductServiceFallbackFactory.class)
public interface RemoteProductService {

	/**
	 * 功能描述: 根据品牌，类目和查询规格 查询spu商品信息
	 * @author: lw
	 * @createTime: 2019/10/18 10:56
	 * @param page
	 * @param spu
	 * @return com.pig4cloud.pig.common.core.util.R<com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.pc.api.entity.Spu>>
	 */
	@GetMapping("/remote/page/product")
	public R<List<Spu>> getSpuPage(@RequestParam("page") Page<Spu> page,@RequestParam("spu") Spu spu,
									@RequestHeader(SecurityConstants.FROM) String From);
	/**
	 * 功能描述: 根据skuId获取sku信息
	 * @author: lw
	 * @createTime: 2019/10/21 9:38
	 * @param id
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@GetMapping("/remote/sku/{id}")
	public R getSkuById(@PathVariable("id") Long id ,@RequestHeader(SecurityConstants.FROM) String From);
	/**
	 * 功能描述: 增加库存
	 * @author: lw
	 * @createTime: 2019/10/18 14:43
	 * @param skuStockList(skuId,skuUpdateby,skuStock要填）
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@PostMapping("/remote/addstock")
	public R addStock(@RequestBody List<SkuStock> skuStockList, @RequestHeader(SecurityConstants.FROM) String From);

	/**
	 * 功能描述: 减少库存
	 * @author: lw
	 * @createTime: 2019/10/18 14:44
	 * @param skuStockList(skuId,skuUpdateby,skuStock要填）
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@PostMapping("/remote/reduceStock")
	public R reduceStock(@RequestBody List<SkuStock> skuStockList,
						 @RequestHeader(SecurityConstants.FROM) String From);

	/**
	 * 功能描述: 根据spuid 增加库存
	 * @author: lw
	 * @createTime: 2019/10/21 9:43
	 * @param spuSaleCountVo
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@PostMapping("/remote/addsalecount")
	public R addSaleCount(@RequestBody SpuSaleCountVo spuSaleCountVo, @RequestHeader(SecurityConstants.FROM) String From);

	/**
	 * 功能描述: 获取类目
	 * @author: lw
	 * @createTime: 2019/10/22 16:23
	 * @param page
	 * @param category
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R<com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.pc.api.entity.Category>>
	 */
	@GetMapping("/remote/page/category")
	public R<List<Category>> getCategoryPage(@RequestParam("page") Page<Category> page,
										  @RequestParam("category") Category category,
											  @RequestHeader(SecurityConstants.FROM) String From);

	/**
	 * 功能描述: 获取品牌
	 * @author: lw
	 * @createTime: 2019/10/22 16:25
	 * @param page
	 * @param brand
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R<com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.pc.api.entity.Brand>>
	 */
	@GetMapping("/remote/page/brand")
	public R<List<Brand>> getBrandPage(@RequestParam("page") Page<Brand> page,@RequestParam("brand") Brand brand,
										@RequestHeader(SecurityConstants.FROM) String From);

	/**
	 * 功能描述: 获取商品类目模板
	 * @author: lw
	 * @createTime: 2019/10/22 16:28
	 * @param page
	 * @param categorySpecificationTemplate
	 * @param From
	 * @return com.pig4cloud.pig.common.core.util.R<com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.pc.api.entity.CategorySpecificationTemplate>>
	 */
	@GetMapping("/remote/page/categoryspecificationtemplate")
	public R<List<CategorySpecificationTemplate>> getCategorySpecificationTemplatePage(@RequestParam("page")
																								Page<CategorySpecificationTemplate> page,
																						@RequestParam("categorySpecificationTemplate") CategorySpecificationTemplate categorySpecificationTemplate,
																						@RequestHeader(SecurityConstants.FROM) String From);

	@PostMapping(ApiConstant.API_REMOTE_PRODUCT_INFO)
	public R<ProductDetailVo> getProductBySpuId(@RequestParam("spuId") Long spuId,@RequestHeader(SecurityConstants.FROM) String From);
}
