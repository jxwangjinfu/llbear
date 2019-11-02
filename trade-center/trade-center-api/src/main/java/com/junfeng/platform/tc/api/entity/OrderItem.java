package com.junfeng.platform.tc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单明细
 *
 * @author fuh
 * @date 2019-09-17 15:11:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tc_order_item")
public class OrderItem extends Model<OrderItem> {
private static final long serialVersionUID = 1L;

    /**
   * id
   */
    @TableId
    private Long id;
    /**
   * 订单号
   */
    private String orderNo;
    /**
   * 商品ID
   */
    private Long spuId;
    private Long skuId;
    /**
   * 商品条码
   */
    private String barCode;
    /**
   * 商品数量
   */
    private Integer goodsNum;
    /**
   * 商品价格
   */
    private Double goodsPrice;
    /**
   * 商品名称
   */
    private String goodsName;
    /**
   * 商品缩略图
   */
    private String goodsThums;
    /**
   * 分润佣金
   */
    private Double commission;
    /**
   * 成本价格
   */
    private Double costPrice;
    /**
   * 总利润
   */
    private Double profit;
    /**
   * 会员价
   */
    private Double memberPrice;
    /**
   * 未打折商品价格合计
   */
    private Double totalGoodsPrice;
    /**
   * 打折后的商品价格合计
   */
    private Double totalGoodsConsume;

}
