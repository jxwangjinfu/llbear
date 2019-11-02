package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 商户信息
 *
 * @author wangk
 * @date 2019-09-19 10:18:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("payment_mch_info")
public class PaymentMchInfo extends Model<PaymentMchInfo> {
private static final long serialVersionUID = 1L;

    /**
   * 商户ID
   */
    @TableId
    private Long id;
    /**
   * 名称
   */
	@NotNull(message = "商户名称不能为空")
    private String name;
    /**
   * 业务系统商户ID 为了兼容，类型为字符
   */
	@NotNull(message = "业务系统商户ID不能为空")
    private String appShopId;
    /**
   * 商户状态,0-停止使用,1-使用中，2-待审核
   */
    private Integer state;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 商户联系人名称
   */
	@NotNull(message = "商户联系人名称不能为空")
    private String userName;
    /**
   * 商户联系人电话
   */
	@NotNull(message = "商户联系人电话不能为空")
    private String phone;
    /**
   * 商户联系人银行卡
   */
	@NotNull(message = "商户联系人银行卡不能为空")
    private String bankCard;
    /**
   * 商户联系人身份证
   */
	@NotNull(message = "商户联系人身份证不能为空")
    private String identityCard;
    /**
   * 商户营业执照号
   */
	@NotNull(message = "商户营业执照号不能为空")
    private String businessLicense;
    /**
   * 身份证正面照片地址
   */
    private String identityCardFrontImgUri;
    /**
   * 身份证反面照片地址
   */
    private String identityCardBackImgUri;
    /**
   * 营业执照照片地址
   */
    private String businessLicenseImgUri;
}
