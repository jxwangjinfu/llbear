package com.junfeng.platform.qc.api.dto;

import lombok.Data;

/**
 * 外部任务参数dto
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月25日 下午3:12:11
 * @version 1.0
 */
@Data
public class OutQuartzParamDTO {
    
    private String outSysName;
    private String callbackUrl;
    private String param;

}
