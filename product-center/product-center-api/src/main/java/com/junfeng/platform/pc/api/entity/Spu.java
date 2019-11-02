package com.junfeng.platform.pc.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu信息
 *
 * @author lw
 * @date 2019-10-21 11:24:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_spu")
public class Spu extends Model<Spu> {
private static final long serialVersionUID = 1L;

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
   * 标题
   */
    private String title;
    /**
   * 子标题
   */
    private String subTitle;
    /**
   * 店铺编号
   */
    private Long shopCode;
    /**
   * 一级类目
   */
    private Long categoryCode;
    /**
   * 品牌编号
   */
    private Long brandCode;
    /**
   * 图片地址列表
   */
    private String images;
    /**
   * 销售数量
   */
    private Long saleCount;
    /**
   * 是否上架
   */
    private String saleFlag;
    /**
   * 可查询规格参数
   */
    private String querySpecification;
    /**
   * '0-正常，1-删除'
   */
	@TableLogic
    private String delFlag;
    /**
   * 包邮 0 不包邮 1 包邮
   */
    private String freeShippingFlag;
    /**
   *
   */
    private LocalDateTime createTime;
    /**
   *
   */
    private String createBy;
    /**
   *
   */
    private LocalDateTime updateTime;
    /**
   *
   */
    private String updateBy;

}
