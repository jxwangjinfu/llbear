package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.SpuDetail;

/**
 * spu详细信息
 *
 * @author lw
 * @date 2019-10-14 15:43:56
 */
public interface SpuDetailService extends IService<SpuDetail> {

  /**
   * spu详细信息简单分页查询
   * @param spuDetail spu详细信息
   * @return
   */
  IPage<SpuDetail> getSpuDetailPage(Page<SpuDetail> page, SpuDetail spuDetail);

  /**
   * 功能描述: 根据spuId删除spu详情
   * @author: lw
   * @createTime: 2019/10/16 9:11
   * @param spuId
   * @return void
   */
   /*boolean logicDeleteBySpuId(Long spuId,String opertorName);*/
}
