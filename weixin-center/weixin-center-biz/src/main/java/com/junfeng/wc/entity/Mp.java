package com.junfeng.wc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公众号
 *
 * @author daiysh
 * @date 2019-09-25 10:53:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wx_mp")
public class Mp extends Model<Mp> {
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
   * appId
   */
    private String appId;
    /**
   * 密钥
   */
    private String secret;
    /**
   * token
   */
    private String token;
    /**
   * 加解密key
   */
    private String aesKey;
  
}
