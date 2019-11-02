package com.junfeng.platform.tc.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.junfeng.platform.tc.api.order.state.OrderContext;
import com.junfeng.platform.tc.api.order.state.OrderEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tc_order")
public class Order extends Model<Order> {
	private static final long serialVersionUID = 1L;


	@TableField(exist = false)
	private List<OrderItem> orderItems;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 订单号
	 */
	private String orderNo;
	private String payOrderNo;
	/**
	 * 订单唯一流水号
	 */
	private String orderUnique;
	private String groupOrderNo;
	/**
	 * 地址所属省区域代码
	 */
	private String areaProvince;
	/**
	 * 地址所属市区域代码
	 */
	private String areaCity;
	/**
	 * 地址所属区县区域代码
	 */
	private String areaCounty;
	/**
	 * 卖家ID
	 */
	private Long sellerId;
	/**
	 * 卖家名称
	 */
	private String sellerName;
	/**
	 * 卖家联系方式
	 */
	private String sellerPhone;
	/**
	 * 订单状态
	 */
	private String state;

	@TableField(exist = false)
	@Setter(AccessLevel.PRIVATE)
	private OrderContext orderContext;

	private OrderContext getOrderContext() {
		return orderContext == null ? (orderContext = OrderContext.create(this.state)) : orderContext;
	}

	public void trigger(OrderEvent event) {
		this.state = this.getOrderContext().trigger(event);
	}

	public String getStateDesc() {
		return this.getOrderContext().getStateDesc();
	}

	/**
	 * 商品总价格
	 */
	private Long totalMoney;

	/**
	 * 总优惠
	**/
	private Long totalDiscount;
	/**
	 * 配送费
	 */
	private Long deliverMoney;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 是否自提
	 */
	private String isSelf;
	/**
	 * 提货时间
	 */
	private LocalDateTime takeGoodsTime;
	/**
	 * 是否支付
	 */
	private String isPay;
	/**
	 * 配送方式
	 */
	private Integer sendType;
	/**
	 * 会员ID
	 */
	private Long userId;
	private Long memberId;
	/**
	 * 会员姓名
	 */
	private String memberName;
	/**
	 * 会员手机号
	 */
	private String memberPhone;
	/**
	 * 收货人姓名
	 */
	private String receiverName;
	/**
	 * 收货人手机
	 */
	private String receiverPhone;
	/**
	 * 详细地址
	 */
	private String receiverAddress;
	/**
	 * 邮编
	 */
	private String postCode;
	/**
	 * 是否需要发票
	 */
	private String isInvoice;
	/**
	 * 发票抬头
	 */
	private String invoiceClient;
	/**
	 * 订单备注
	 */
	private String remarks;
	/**
	 * 下单时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否退款
	 */
	private String isRefund;
	/**
	 * 订单来源
	 */
	private String orderSrc;
	/**
	 * 最后更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 门牌号
	 */
	private String addressName;
	/**
	 * 该订单商品总计
	 */
	private Integer totalNum;

}
