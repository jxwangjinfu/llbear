package com.junfeng.platform.pc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.SkuStockRecord;
import com.junfeng.platform.pc.mapper.SkuStockRecordMapper;
import com.junfeng.platform.pc.service.SkuStockRecordService;

/**
 * 库存变动流水
 *
 * @author lw
 * @date 2019-10-15 16:44:39
 */
@Service("skuStockRecordService")
public class SkuStockRecordServiceImpl extends ServiceImpl<SkuStockRecordMapper, SkuStockRecord> implements SkuStockRecordService {

  /**
   * 库存变动流水简单分页查询
   * @param skuStockRecord 库存变动流水
   * @return
   */
  @Override
  public IPage<SkuStockRecord> getSkuStockRecordPage(Page<SkuStockRecord> page, SkuStockRecord skuStockRecord){
      return baseMapper.getSkuStockRecordPage(page,skuStockRecord);
  }
}
