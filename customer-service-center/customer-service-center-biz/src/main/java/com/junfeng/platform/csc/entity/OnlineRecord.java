package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 人工在线服务记录表
 *
 * @author lw
 * @date 2019-09-29 10:48:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_online_record")
public class OnlineRecord extends Model<OnlineRecord> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Long id;
    /**
   * 
   */
    private Long userCode;
    /**
   * 
   */
    private Long memberCode;
    /**
   * 聊天状态 0：开始 1：结束
   */
    private Integer onlineType;
    /**
   * 
   */
    private LocalDateTime createTime;
  
}
