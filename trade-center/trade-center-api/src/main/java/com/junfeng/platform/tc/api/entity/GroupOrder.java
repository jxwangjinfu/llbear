package com.junfeng.platform.tc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 团购订单
 *
 * @author wangk
 * @date 2019-10-22 16:17:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tc_group_order")
public class GroupOrder extends Model<GroupOrder> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 团购ID
     */
    private String groupOrderNo;
    /**
     * 订单号
     */
    private String tradeOrderNo;

    private Long spuId;
    private Long skuId;
    /**
     * 是否为团长 1表示团长 0表示团员
     */
    private String role;

    private String state;
    /**
     * 团购的用户
     */
    private Long userId;
    private Long memberId;

    /**
     * 团人员限制数量
     **/
    private int joinLimitNum;
    /**
     * 已加入团人数
     **/
    private int joinedNum;

    private int expireHour;

    private Long sellerId;

    // 单品数量
    private Integer goodsNum;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    private String delFlag;

}
