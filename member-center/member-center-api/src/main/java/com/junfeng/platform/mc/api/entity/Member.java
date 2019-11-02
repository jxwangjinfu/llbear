package com.junfeng.platform.mc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 会员表
 *
 * @author daiysh
 * @date 2019-09-23 09:22:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mc_member")
public class Member extends Model<Member> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 关联用户Id
	 */
	private Integer userId;
	/**
	 * 身份Id
	 */
	private String identityId;
	/**
	 * 微信账号
	 */
	private String openId;
	/**
	 * 范围
	 */
	private String scope;
	/**
	 * 会员级别值
	 */
	private Integer levelValue;
	/**
	 * 会员积分
	 */
	private Integer points;
	/**
	 * 余额
	 */
	private Integer balance;
	/**
	 * 性别  1：男  2：女
	 */
	private Integer gender;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 生日
	 */
	private String birth;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 删除标记
	 */
	private String delFlag;

}
