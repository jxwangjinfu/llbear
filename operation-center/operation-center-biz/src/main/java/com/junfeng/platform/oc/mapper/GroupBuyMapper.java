package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.GroupBuy;
import org.apache.ibatis.annotations.Param;

/**
 * 团购活动表
 *
 * @author wangjf
 * @date 2019-10-22 14:05:08
 */
public interface GroupBuyMapper extends BaseMapper<GroupBuy> {
  /**
    * 团购活动表简单分页查询
    * @param groupBuy 团购活动表
    * @return
    */
  IPage<GroupBuy> getGroupBuyPage(Page page, @Param("groupBuy") GroupBuy groupBuy);


}
