package com.junfeng.platform.oc.api.result;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 拼团返回结果
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/22 14:23
 * @projectName pig
 */
@Data
public class GroupBuyResult {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 系统来源
     */
    private String clientId;
    /**
     * 团购活动名称
     */
    private String groupName;
    /**
     * 赠品状态,0代表未开始、1代表进行中、-1代表结束
     */
    private Integer state;
    /**
     * 团购人数
     */
    private Integer personNumber;
    /**
     * 团购类型
     */
    private Integer groupType;
    /**
     * 关联SKU主键
     */
    private Long pcSkuId;
    /**
     * 团购有效期
     */
    private Integer effectiveTime;
    /**
     * 开始时间
     */
    private LocalDateTime useStartTime;
    /**
     * 结束时间
     */
    private LocalDateTime useEndTime;
    /**
     * 原价,单位分
     */
    private Long price;
    /**
     * 团购价,单位分
     */
    private Long groupPrice;
    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 最后变更时间
     */
    private LocalDateTime updateTime;
    /**
     * 最后更新人员
     */
    private String updateBy;

}
