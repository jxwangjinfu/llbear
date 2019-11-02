package com.junfeng.platform.pc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.SkuStock;
import com.junfeng.platform.pc.api.entity.SkuStockRecord;
import com.junfeng.platform.pc.mapper.SkuStockMapper;
import com.junfeng.platform.pc.service.SkuStockRecordService;
import com.junfeng.platform.pc.service.SkuStockService;

import lombok.AllArgsConstructor;

/**
 * sku库存表
 *
 * @author lw
 * @date 2019-10-14 15:41:03
 */
@Service("skuStockService")
@AllArgsConstructor
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

	private final SkuStockRecordService skuStockRecordService;

	/**
	 * sku库存表简单分页查询
	 *
	 * @param skuStock sku库存表
	 * @return
	 */
	@Override
	public IPage<SkuStock> getSkuStockPage(Page<SkuStock> page, SkuStock skuStock) {
		return baseMapper.getSkuStockPage(page, skuStock);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean addSkuStock(List<SkuStock> skuStockList) {
		return StockUpdate(skuStockList, 2);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean reduceSkuStock(List<SkuStock> skuStockList) {
		return StockUpdate(skuStockList, 1);
	}

	private boolean StockUpdate(List<SkuStock> skuStockList, int intStockType) {
		for (SkuStock skuStockTmp : skuStockList) {
			synchronized (this) {
				SkuStock skuStock = baseMapper.selectById(skuStockTmp.getSkuCode());
				if (skuStock == null ||
					skuStock.getStock() < skuStockTmp.getStock() || "0".equals(skuStock.getDelFlag()) == false) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return false;
				}
				int afterCount;
				if (intStockType == 1) {
					afterCount = skuStock.getStock() - skuStockTmp.getStock();
				} else {
					afterCount = skuStock.getStock() + skuStockTmp.getStock();
				}
				SkuStockRecord skuStockRecord = new SkuStockRecord();
				skuStockRecord.setStockType("" + intStockType);
				skuStockRecord.setSkuCode(skuStock.getSkuCode());
				skuStockRecord.setCreateBy(skuStockTmp.getUpdateBy());
				skuStockRecord.setCountBefore(skuStock.getStock());
				skuStockRecord.setCountAfter(afterCount);
				skuStockRecord.setCount(skuStockTmp.getStock());
				skuStockRecordService.save(skuStockRecord);

				SkuStock skuStockUpdate = new SkuStock();
				skuStockUpdate.setStock(afterCount);
				skuStockUpdate.setUpdateBy(skuStockTmp.getUpdateBy());
				skuStockUpdate.setSkuCode(skuStock.getSkuCode());
				super.updateById(skuStockUpdate);
			}
		}
		return true;
	}


}
