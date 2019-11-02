package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.Sku;

import java.util.List;

/**
 * sku信息
 *
 * @author lw
 * @date 2019-10-12 18:25:42
 */
public interface SkuService extends IService<Sku> {

	/**
	 * 功能描述: 根据spuId删除sku
	 * @author: lw
	 * @createTime: 2019/10/16 9:13
	 * @param spuId
	 * @return boolean
	 */
	boolean deleteBySpuId(Long spuId);

	/**
	 * 功能描述: 根据spuid获取sku列表
	 * @author: lw
	 * @createTime: 2019/10/28 15:42
	 * @param spuId
	 * @return java.util.List<com.junfeng.platform.pc.entity.Sku>
	 */
	List<Sku> getSkuListBySpuId(Long spuId);
}
