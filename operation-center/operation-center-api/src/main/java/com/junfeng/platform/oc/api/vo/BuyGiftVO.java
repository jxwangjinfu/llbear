package com.junfeng.platform.oc.api.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 买赠新增VO
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/25 14:17
 * @projectName pig
 */
@Data
public class BuyGiftVO {
    private String clientId;
    private String giftName;
    private Integer usableGoods;
    private LocalDateTime useStartTime;
    private LocalDateTime useEndTime;
    private Integer levelType;
    private List<BuyGiftLevelVO> levelList;
}
