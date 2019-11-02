package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;

/**
 * spu信息
 *
 * @author lw
 * @date 2019-10-14 15:43:49
 */
public interface SpuService extends IService<Spu> {

  /**
   * spu信息简单分页查询
   * @param spu spu信息
   * @return
   */
  IPage<Spu> getSpuPage(Page<Spu> page, Spu spu);
  /**
   * 功能描述: 根据spuId删除spu
   * @author: lw
   * @createTime: 2019/10/16 9:10
   * @param spuId
   * @return void
   */
  /*boolean logicDeleteBySpuId(Long spuId,String opertorName);*/

  /**
   * 功能描述: 根据spuId增加销售量
   * @author: lw
   * @createTime: 2019/10/18 16:29
   * @param spuId
   * @param saleCount
   * @return boolean
   */
  boolean addSaleCountBySpuId(SpuSaleCountVo spuSaleCountVo);
	/**
	 * 商品上架
	 * @author: lw
	 * @createTime: 2019/10/23 16:00
	 * @param spuId
	 * @return boolean
	 */
	boolean on(Long spuId);
	/**
	 * 商品下架
	 * @author: lw
	 * @createTime: 2019/10/23 16:01
	 * @param spuId
	 * @return boolean
	 */
	boolean off(Long spuId);
}
