package com.junfeng.platform.sc.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.sc.entity.Shop;
import com.junfeng.platform.sc.entity.ShopType;
import com.junfeng.platform.sc.entity.ShopUserRelation;
import com.junfeng.platform.sc.mapper.ShopMapper;
import com.junfeng.platform.sc.service.ShopService;
import com.junfeng.platform.sc.service.ShopTypeService;
import com.junfeng.platform.sc.service.ShopUserRelationService;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 店铺信息
 *
 * @author lw
 * @date 2019-10-21 13:49:30
 */
@Service("shopService")
@AllArgsConstructor
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

	private final ShopUserRelationService shopUserRelationService;
	private final ShopTypeService shopTypeService;

	/**
	 * 店铺信息简单分页查询
	 *
	 * @param shop 店铺信息
	 * @return
	 */
	@Override
	public IPage<Shop> getShopPage(Page<Shop> page, Shop shop) {
		return baseMapper.getShopPage(page, shop);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean AddShop(Shop shop) {
		checkShop(shop);
		Assert.isTrue(save(shop),"店铺添加失败");

		ShopUserRelation shopUserRelation = new ShopUserRelation();
		shopUserRelation.setShopCode(shop.getId());
		shopUserRelation.setUserCode(SecurityUtils.getUser().getId().longValue());
		shopUserRelation.setUserType("0");
		Assert.isTrue(shopUserRelationService.save(shopUserRelation));
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateByIdWithCheck(Shop entity) {
		checkShop(entity);
		return updateById(entity);
	}

	private void checkShop(Shop entity) {
		ShopType shopType = shopTypeService.getById(entity.getTypeCode());
		Assert.notNull(shopType, "店铺类型错误");
	}

}
