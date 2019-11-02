package com.junfeng.platform.tc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.tc.api.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

/**
 * 订单明细
 *
 * @author fuh
 * @date 2019-09-17 15:11:56
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
	/**
	 * 订单明细简单分页查询
	 *
	 * @param orderItem 订单明细
	 * @return
	 */
	IPage<OrderItem> getOrderItemPage(Page page, @Param("orderItem") OrderItem orderItem);


}
