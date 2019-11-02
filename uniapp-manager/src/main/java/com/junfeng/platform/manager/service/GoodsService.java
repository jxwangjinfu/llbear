package com.junfeng.platform.manager.service;


import com.junfeng.platform.manager.result.GoodsResult;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;

/**
 * 商品业务处理层
 *
 * @author daiysh
 * @date 2019-10-15 17:00:11
 */
public interface GoodsService {

	R<List<GoodsResult>> getGoodsList(Spu spu);

	R<ProductDetailVo> getGoodsDetail(long goodsId);

	R<Object> getCateList();
}
