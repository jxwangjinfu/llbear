package com.junfeng.platform.dc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.api.vo.CommandVo;
import com.junfeng.platform.dc.entity.Command;

/**
 * 指令
 *
 * @author fuh
 * @date 2019-09-18 16:09:31
 */
public interface CommandMapper extends BaseMapper<Command> {
    /**
     * 指令简单分页查询
     * 
     * @param command
     *            指令
     * @return
     */
    IPage<Command> getCommandPage(Page<?> page, @Param("command") Command command);

    int cancel(@Param("commandId") Long commandId, @Param("deviceId") Long deviceId);
    
    List<CommandVo> getUndoneCommands(Long deviceId);

}
