package com.junfeng.platform.payment.config.mq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 回调业务系统消息定义
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月14日 下午1:47:04
 * @version 1.0
 */
@Data
public class NotifyMessage {
    private String msgId;
    private String tradeOrderNo;
    private String mchOrderNo;
    private String payOrderNo;
    private Long payMchId;
    private Long amount;
    private String notifyUrl;
    private String payChannelCode;
    private String paymentModeCode;
    private Integer state;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paySuccessTime;
    private Long paySuccessTimestamp;
    private Integer failCount;

}
