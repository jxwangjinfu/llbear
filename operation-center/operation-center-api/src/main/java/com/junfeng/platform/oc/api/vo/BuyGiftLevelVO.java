package com.junfeng.platform.oc.api.vo;

import lombok.Data;

import java.util.List;

/**
 * 买赠等级
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/25 14:20
 * @projectName pig
 */
@Data
public class BuyGiftLevelVO {
    private Integer orderUsePre;
    private Integer points;
    private List<BuyGiftLevelGoodsVO> goodsList;
}
