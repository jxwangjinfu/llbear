package com.junfeng.platform.tc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.tc.api.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
public interface OrderMapper extends BaseMapper<Order> {
  /**
    * 订单简单分页查询
    * @param order 订单
    * @return
    */
  IPage<Order> getOrderPage(Page page, @Param("order") Order order);


}
