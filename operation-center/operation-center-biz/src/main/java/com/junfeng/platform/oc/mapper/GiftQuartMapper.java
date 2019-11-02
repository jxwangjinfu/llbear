package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.GiftQuart;
import org.apache.ibatis.annotations.Param;

/**
 * 赠品定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-12 14:55:40
 */
public interface GiftQuartMapper extends BaseMapper<GiftQuart> {
  /**
    * 赠品定时任务触发表简单分页查询
    * @param giftQuart 赠品定时任务触发表
    * @return
    */
  IPage<GiftQuart> getGiftQuartPage(Page page, @Param("giftQuart") GiftQuart giftQuart);


}
