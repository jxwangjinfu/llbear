package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.SkuStockRecord;

/**
 * 库存变动流水
 *
 * @author lw
 * @date 2019-10-15 16:44:39
 */
public interface SkuStockRecordService extends IService<SkuStockRecord> {

  /**
   * 库存变动流水简单分页查询
   * @param skuStockRecord 库存变动流水
   * @return
   */
  IPage<SkuStockRecord> getSkuStockRecordPage(Page<SkuStockRecord> page, SkuStockRecord skuStockRecord);


}
