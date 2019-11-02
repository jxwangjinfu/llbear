package com.junfeng.platform.sc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.sc.entity.ShopType;
import com.junfeng.platform.sc.mapper.ShopTypeMapper;
import com.junfeng.platform.sc.service.ShopTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 店铺类型
 *
 * @author lw
 * @date 2019-10-23 14:57:35
 */
@Service("shopTypeService")
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements ShopTypeService {


	/**
	 * 店铺类型简单分页查询
	 *
	 * @param shopType 店铺类型
	 * @return
	 */
	@Override
	public IPage<ShopType> getShopTypePage(Page<ShopType> page, ShopType shopType) {
		return baseMapper.getShopTypePage(page, shopType);
	}

	@Override
	public boolean saveWithCheck(ShopType entity) {
		checkShopType(entity);
		return save(entity);
	}

	private void checkShopType(ShopType entity) {
		ShopType userGroup = this.getOne(new QueryWrapper<ShopType>().lambda().eq(ShopType::getName
			, entity.getName()));
		Assert.isNull(userGroup, "类型名称已存在!");
	}
}
