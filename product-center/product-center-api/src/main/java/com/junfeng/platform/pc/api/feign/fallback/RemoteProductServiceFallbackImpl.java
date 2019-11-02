package com.junfeng.platform.pc.api.feign.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.*;
import com.junfeng.platform.pc.api.feign.RemoteProductService;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/17 17:56
 * @projectName pig
 */
@Slf4j
@Component
public class RemoteProductServiceFallbackImpl implements RemoteProductService {

	@Setter
	private Throwable cause;


	@Override
	public R<List<Spu>> getSpuPage(Page<Spu> page, Spu spu, String From) {
		log.error("feign 查询spu商品", cause);
		return R.feignFailed("查询spu商品失败");
	}

	@Override
	public R getSkuById(Long id, String From) {
		log.error("feign 根据skuid查询商品", cause);
		return R.feignFailed("根据skuid查询商品失败");
	}

	@Override
	public R addStock(List<SkuStock> skuStockList, String From) {
		log.error("feign 增加库存", cause);
		return R.feignFailed("增加库存失败");
	}

	@Override
	public R reduceStock(List<SkuStock> skuStockList, String From) {
		log.error("feign 减少库存", cause);
		return R.feignFailed("减少库存失败");
	}

	@Override
	public R addSaleCount(SpuSaleCountVo spuSaleCountVo, String From) {
		log.error("feign 增加销售数量", cause);
		return R.feignFailed("增加销售数量失败");
	}

	@Override
	public R<List<Category>> getCategoryPage(Page<Category> page, Category category, String From) {
		log.error("feign 类目查询", cause);
		return R.feignFailed("类目查询失败");
	}

	@Override
	public R<List<Brand>> getBrandPage(Page<Brand> page, Brand brand, String From) {
		log.error("feign 品牌查询", cause);
		return R.feignFailed("品牌查询失败");
	}

	@Override
	public R<List<CategorySpecificationTemplate>> getCategorySpecificationTemplatePage(Page<CategorySpecificationTemplate> page,
																					   CategorySpecificationTemplate categorySpecificationTemplate,
																					   String From) {
		log.error("feign 类目模板查询", cause);
		return R.feignFailed("类目模板查询失败");
	}

	@Override
	public R<ProductDetailVo> getProductBySpuId(Long spuId, String From) {
		log.error("feign 查询商品失败", cause);
		return R.feignFailed("查询商品失败");
	}
}
