package com.junfeng.platform.pc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.vo.SpuSaleCountVo;
import com.junfeng.platform.pc.mapper.SpuMapper;
import com.junfeng.platform.pc.service.SpuService;

/**
 * spu信息
 *
 * @author lw
 * @date 2019-10-14 15:43:49
 */
@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

  /**
   * spu信息简单分页查询
   * @param spu spu信息
   * @return
   */
  @Override
  public IPage<Spu> getSpuPage(Page<Spu> page, Spu spu){
      return baseMapper.getSpuPage(page,spu);
  }

	@Override
	public boolean addSaleCountBySpuId(SpuSaleCountVo spuSaleCountVo) {
		return new LambdaUpdateChainWrapper<Spu>(super.baseMapper)
			.eq(Spu::getId,spuSaleCountVo.getSpuId())
			.setSql(" sale_count=sale_count+"+spuSaleCountVo.getSaleCount()+" ")
			.update();
	}

	@Override
	public boolean on(Long spuId) {
		return new LambdaUpdateChainWrapper<Spu>(baseMapper)
			.eq(Spu::getId,spuId)
			.set(Spu::getSaleFlag,"1").update();
	}

	@Override
	public boolean off(Long spuId) {
		return new LambdaUpdateChainWrapper<Spu>(baseMapper)
			.eq(Spu::getId,spuId)
			.set(Spu::getSaleFlag,"0").update();
	}
}
