package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.GiftQuart;
import com.junfeng.platform.oc.service.GiftQuartService;
import com.junfeng.platform.oc.mapper.GiftQuartMapper;
import org.springframework.stereotype.Service;

/**
 * 赠品定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-12 14:55:40
 */
@Service("giftQuartService")
public class GiftQuartServiceImpl extends ServiceImpl<GiftQuartMapper, GiftQuart> implements GiftQuartService {

  /**
   * 赠品定时任务触发表简单分页查询
   * @param giftQuart 赠品定时任务触发表
   * @return
   */
  @Override
  public IPage<GiftQuart> getGiftQuartPage(Page<GiftQuart> page, GiftQuart giftQuart){
      return baseMapper.getGiftQuartPage(page,giftQuart);
  }

	/**
	 * 修改赠品状态
	 *
	 * @param id
	 * @return void
	 * @author: wangjf
	 * @createTime: 2019/10/12 15:28
	 */
	@Override
	public Boolean giftUpdateState(Long id) {
		//TODO
		return Boolean.TRUE;
	}


}
