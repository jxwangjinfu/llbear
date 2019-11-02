package com.junfeng.platform.pc.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.Sku;
import com.junfeng.platform.pc.mapper.SkuMapper;
import com.junfeng.platform.pc.service.SkuService;
import com.junfeng.platform.pc.service.SkuStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * sku信息
 *
 * @author lw
 * @date 2019-10-12 18:25:42
 */
@Service("skuService")
@AllArgsConstructor
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

	private final SkuStockService skuStockService;


	@Override
	@Transactional(readOnly = false)
	public boolean deleteBySpuId(Long spuId) {
		List<Sku> skuList = new LambdaQueryChainWrapper<Sku>(super.baseMapper)
			.eq(Sku::getSpuCode, spuId)
			.list();

		skuList.forEach(sku -> {
			delById(sku.getId());
		});
		return true;
	}

	@Override
	public List<Sku> getSkuListBySpuId(Long spuId) {
		return new LambdaQueryChainWrapper<Sku>(super.baseMapper)
			.eq(Sku::getSpuCode,spuId)
			.list();
	}

	public boolean removeById(Serializable id) {
		return delById(id);
	}


	private boolean delById(Serializable id) {
		skuStockService.removeById(id);
		return super.removeById(id);
	}
}
