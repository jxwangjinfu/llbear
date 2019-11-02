package com.junfeng.platform.dc.api.vo;

import lombok.Data;

/**
 * 创建一条指令
 * 
 * @projectName:device-center-api
 * @author:fuh
 * @date:2019年9月18日 下午5:20:30
 * @version 1.0
 */
@Data
public class CreateCommandVo {

    private String command;

    private Long[] deviceIds;
}
