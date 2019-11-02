/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.junfeng.platform.dc.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.api.result.UndoneCommands;
import com.junfeng.platform.dc.api.vo.CommandVo;
import com.junfeng.platform.dc.api.vo.CreateCommandVo;
import com.junfeng.platform.dc.entity.Command;
import com.junfeng.platform.dc.entity.CommandDetail;
import com.junfeng.platform.dc.mapper.CommandMapper;
import com.junfeng.platform.dc.service.CommandDetailService;
import com.junfeng.platform.dc.service.CommandService;

import lombok.AllArgsConstructor;

/**
 * 指令
 *
 * @author fuh
 * @date 2019-09-18 16:09:31
 */
@Service("commandService")
@AllArgsConstructor
public class CommandServiceImpl extends ServiceImpl<CommandMapper, Command> implements CommandService {

    private CommandDetailService commandDetailService;

    /**
     * 指令简单分页查询
     * 
     * @param command
     *            指令
     * @return
     */
    @Override
    public IPage<Command> getCommandPage(Page<Command> page, Command command) {
        return baseMapper.getCommandPage(page, command);
    }

    @Override
    public boolean sendCommand(CreateCommandVo createCommandVo) {

        // 插入command
        Command command = new Command();
        command.setType(createCommandVo.getCommand());
        command.setName(createCommandVo.getCommand());
        command.setTotalNum(createCommandVo.getDeviceIds().length);
        command.setUndoneNum(createCommandVo.getDeviceIds().length);
        // shopCommand.setCreator(UserContext.getUser().getId());
        command.setStatus("1");
        boolean save = save(command);

        Arrays.stream(createCommandVo.getDeviceIds()).forEach(deviceId -> {
            CommandDetail shopCommandDetail = new CommandDetail();
            shopCommandDetail.setDeviceId(deviceId);
            shopCommandDetail.setCommandId(command.getId());
            shopCommandDetail.setStatus("0");
            commandDetailService.save(shopCommandDetail);
        });

        return save;
    }

    @Override
    public int cancel(Long commandId, Long deviceId) {

        Command shopCommand = this.getById(commandId);
        int cancel = this.baseMapper.cancel(commandId, deviceId);
        if (deviceId == null || deviceId == 0) {
            Command update = new Command();
            update.setId(commandId);
            update.setCancelTime(LocalDateTime.now());
            update.setCancelNum(shopCommand.getCancelNum() + cancel);
            update.setUndoneNum(shopCommand.getUndoneNum() - cancel);
            update.setStatus("3");
            this.baseMapper.updateById(update);
        } else {
            Command update = new Command();
            update.setId(commandId);
            update.setCancelNum(shopCommand.getCancelNum() + cancel);
            update.setUndoneNum(shopCommand.getUndoneNum() - cancel);
            this.baseMapper.updateById(update);
        }
        return cancel;
    }

    @Override
    public UndoneCommands getUndoneCommands(Long deviceId) {
        UndoneCommands undoneCommands = new UndoneCommands();
        undoneCommands.setDeviceId(deviceId);

        // 查询这家店所有的未处理的指令
        List<CommandVo> list = this.baseMapper.getUndoneCommands(deviceId);
        undoneCommands.setCommands(list);

        return undoneCommands;
    }

    @Override
    public boolean done(Long deviceId, Long commandId) {
     // 更新指令完成状态
        commandDetailService.done(deviceId, commandId);

        // 更新指令
        Command command = this.getById(commandId);
        command.setDoneNum(command.getDoneNum() + 1);
        command.setUndoneNum(command.getUndoneNum() - 1);
        if (command.getUndoneNum() == 0) {
            // 全部完成
            command.setStatus("2");
        }
        command.setUpdateTime(null);
        return this.updateById(command);
    }

}
