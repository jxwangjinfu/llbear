package com.junfeng.platform.pc.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu详细信息
 *
 * @author lw
 * @date 2019-10-14 15:43:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_spu_detail")
public class SpuDetail extends Model<SpuDetail> {
private static final long serialVersionUID = 1L;

    /**
   * spuId
   */
    @TableId(type = IdType.INPUT)
    private Long spuCode;
    /**
   * 描述
   */
    private String description;
    /**
   * 规格参数
   */
    private String specifications;
    /**
   * 特殊规格参数
   */
    private String specialSpecifications;
    /**
   * 包装清单
   */
    private String packingList;
    /**
   * 售后服务
   */
    private String afterService;
    /**
   * 快递费用
   */
    private Long shippingFee;
    /**
   * '0-正常，1-删除'
   */
	@TableLogic
    private String delFlag;
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
