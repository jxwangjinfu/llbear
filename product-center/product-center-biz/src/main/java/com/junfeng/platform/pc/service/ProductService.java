package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.ProductCreateVo;
import com.junfeng.platform.pc.api.vo.ProductDetailVo;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/15 11:00
 * @projectName pig
 */
public interface ProductService {

	/**
	 * 功能描述: 新增产品
	 * @author: lw
	 * @createTime: 2019/10/15 11:07
	 * @param productVo
	 * @return boolean
	 */
	boolean add(ProductCreateVo productVo);
	/**
	 * 功能描述: 修改产品
	 * @author: lw
	 * @createTime: 2019/10/15 11:08
	 * @param productVo
	 * @return boolean
	 */
	boolean update(ProductCreateVo productVo);
	/**
	 * 功能描述: 根据spuId删除整个商品信息
	 * @author: lw
	 * @createTime: 2019/10/16 9:06
	 * @param spuId
	 * @return boolean
	 */
	boolean delete(Long spuId);

	/**
	 * 功能描述: 根据条件查询
	 * @author: lw
	 * @createTime: 2019/10/16 17:20
	 * @param page
	 * @param Spu
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.pc.entity.Spu>
	 */
	IPage<Spu> getSpuPage(Page<Spu> page, Spu spu);
	/**
	 * 功能描述: 根据skuId获取商品相关信息并SKU中只包含自己
	 * @author: lw
	 * @createTime: 2019/10/21 10:38
	 * @param skuId
	 * @return com.junfeng.platform.pc.controller.model.ProductVo
	 */
	ProductDetailVo getProductOnlySelfBySkuId(Long skuId);
	/**
	 * 功能描述: 根据skuId获取商品相关信息并SKU中只包含自己
	 * @author: lw
	 * @createTime: 2019/10/21 10:38
	 * @param skuId
	 * @return com.junfeng.platform.pc.controller.model.ProductVo
	 */
	ProductDetailVo getProductBySpuId(Long spuId);
	/**
	 * 商品上架
	 * @author: lw
	 * @createTime: 2019/10/23 15:57
	 * @param spuId
	 * @return boolean
	 */
	boolean onProduct(Long spuId);
	/**
	 * 商品下架
	 * @author: lw
	 * @createTime: 2019/10/23 15:57
	 * @param spuId
	 * @return boolean
	 */
	boolean offProduct(Long spuId);
}
