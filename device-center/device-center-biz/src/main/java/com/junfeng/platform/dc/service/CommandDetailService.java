package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.CommandDetail;

/**
 * 指令明细
 *
 * @author fuh
 * @date 2019-09-18 17:30:01
 */
public interface CommandDetailService extends IService<CommandDetail> {

    /**
     * 指令明细简单分页查询
     * 
     * @param commandDetail
     *            指令明细
     * @return
     */
    IPage<CommandDetail> getCommandDetailPage(Page<CommandDetail> page, CommandDetail commandDetail);

    void done(Long deviceId, Long commandId);

}
