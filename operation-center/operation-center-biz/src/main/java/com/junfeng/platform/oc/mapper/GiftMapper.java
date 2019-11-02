package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.Gift;
import org.apache.ibatis.annotations.Param;

/**
 * 赠品表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
public interface GiftMapper extends BaseMapper<Gift> {
  /**
    * 赠品表简单分页查询
    * @param gift 赠品表
    * @return
    */
  IPage<Gift> getGiftPage(Page page, @Param("gift") Gift gift);


}
