package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.CommandDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 指令明细
 *
 * @author fuh
 * @date 2019-09-18 17:30:01
 */
public interface CommandDetailMapper extends BaseMapper<CommandDetail> {
    /**
     * 指令明细简单分页查询
     * 
     * @param commandDetail
     *            指令明细
     * @return
     */
    IPage<CommandDetail> getCommandDetailPage(Page<?> page, @Param("commandDetail") CommandDetail commandDetail);

    void done(@Param("deviceId") Long deviceId, @Param("commandId") Long commandId);
}
