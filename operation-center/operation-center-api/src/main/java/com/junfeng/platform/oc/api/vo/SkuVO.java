package com.junfeng.platform.oc.api.vo;

import lombok.Data;

/**
 * 销售商品信息
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 11:12
 * @projectName pig
 */
@Data
public class SkuVO {

	//skuId
    private Long id;
    private Long spuCode;
    //单价
    private Long price;
    private String skuCode;
    //数量
    private Integer amount;
    //总价
    private Long totalPrice;

}
