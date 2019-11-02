package com.junfeng.platform.dc.api.result;

import java.util.List;

import com.junfeng.platform.dc.api.vo.CommandVo;

import lombok.Data;

/**
 * 待执行的指令
 * 
 * @projectName:device-center-api
 * @author:fuh
 * @date:2019年9月18日 下午5:23:37
 * @version 1.0
 */
@Data
public class UndoneCommands {

    private Long deviceId;
    private List<CommandVo> commands;

}
