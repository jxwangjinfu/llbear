package com.junfeng.platform.manager.service.imp;

import com.google.common.collect.Lists;
import com.junfeng.platform.manager.result.CateResult;
import com.junfeng.platform.manager.result.GoodsResult;
import com.junfeng.platform.manager.service.GoodsService;
import com.junfeng.platform.pc.api.entity.Category;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.feign.RemoteProductService;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品业务处理实现类
 *
 * @author daiysh
 * @date 2019-10-15 17:12
 **/
@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	private final RemoteProductService remoteProductService;

	@Override
	public R<List<GoodsResult>> getGoodsList(Spu spu) {
		R<List<Spu>> r = remoteProductService.getSpuPage(null,spu,SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), r.getMsg());
		List<Spu> spuList = r.getData();
		List<GoodsResult> resultList = transferBy(spuList);

		return R.ok(resultList);
	}

	@Override
	public R<ProductDetailVo> getGoodsDetail(long goodsId) {
		R<ProductDetailVo> productResultVoR = remoteProductService.getProductBySpuId(goodsId,SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(productResultVoR.getCode()), "获取商品详情失败");

		return productResultVoR;
	}

	@Override
	public R<Object> getCateList() {
		R<List<Category>> r = remoteProductService.getCategoryPage(null, null, SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "获取类目列表失败");
		List<Category> categoryList = r.getData();
		List<CateResult> resultList = transfer2CateResultBy(categoryList);

		return R.ok(resultList);
	}

	/**
	 * 组装类目数据
	 *
	 * @param categoryList
	 * @return: java.util.List<com.junfeng.platform.manager.result.CateResult>
	 * @author: daiysh
	 * @createTime: 2019-10-29  14:41
	 **/
	private List<CateResult> transfer2CateResultBy(List<Category> categoryList) {
		List<CateResult> resultList = Lists.newArrayList();

		if (!CollectionUtils.isEmpty(categoryList)) {
			categoryList.forEach(e->{
				CateResult transfer = CateResult.transfer(e);
				resultList.add(transfer);
			});
		}

		return resultList;
	}

	/**
	 * 组装商品数据
	 *
	 * @param goodsList
	 * @return: java.util.List<com.junfeng.platform.manager.result.OrderResult>
	 * @author: daiysh
	 * @createTime: 2019-10-26  12:46
	 **/
	private List<GoodsResult> transferBy(List<Spu> goodsList) {
		List<GoodsResult> resultList = Lists.newArrayList();

		if (!CollectionUtils.isEmpty(goodsList)) {
			goodsList.forEach(e -> {
				GoodsResult transfer = GoodsResult.transfer(e);
				resultList.add(transfer);
			});
		}

		return resultList;
	}
}
