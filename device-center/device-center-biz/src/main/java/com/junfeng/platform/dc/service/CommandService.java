package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.api.result.UndoneCommands;
import com.junfeng.platform.dc.api.vo.CreateCommandVo;
import com.junfeng.platform.dc.entity.Command;

/**
 * 指令
 *
 * @author fuh
 * @date 2019-09-18 16:09:31
 */
public interface CommandService extends IService<Command> {

    /**
     * 指令简单分页查询
     * 
     * @param command
     *            指令
     * @return
     */
    IPage<Command> getCommandPage(Page<Command> page, Command command);

    boolean sendCommand(CreateCommandVo sendCommandVo);

    int cancel(Long commandId, Long deviceId);

    UndoneCommands getUndoneCommands(Long id);

    boolean done(Long deviceId, Long commandId);

}
