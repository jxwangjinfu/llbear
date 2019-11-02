package com.junfeng.wc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 开放平台
 *
 * @author daiysh
 * @date 2019-09-25 10:55:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wx_open")
public class Open extends Model<Open> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Long id;
    /**
   * 名称
   */
    private String name;
    /**
   * token
   */
    private String componentToken;
    /**
   * 加解密key
   */
    private String componentAesKey;
    /**
   * componentAppId
   */
    private String componentAppId;
    /**
   * 密钥
   */
    private String componentSecret;
    /**
   * 授权账号
   */
    private String authorizerAppId;
    /**
   * 授权账号名称
   */
    private String authorizerName;
  
}
