package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.entity.CommandDetail;
import com.junfeng.platform.dc.service.CommandDetailService;
import com.junfeng.platform.dc.mapper.CommandDetailMapper;
import org.springframework.stereotype.Service;

/**
 * 指令明细
 *
 * @author fuh
 * @date 2019-09-18 17:30:01
 */
@Service("commandDetailService")
public class CommandDetailServiceImpl extends ServiceImpl<CommandDetailMapper, CommandDetail>
        implements CommandDetailService {

    /**
     * 指令明细简单分页查询
     * 
     * @param commandDetail
     *            指令明细
     * @return
     */
    @Override
    public IPage<CommandDetail> getCommandDetailPage(Page<CommandDetail> page, CommandDetail commandDetail) {
        return baseMapper.getCommandDetailPage(page, commandDetail);
    }

    @Override
    public void done(Long deviceId, Long commandId) {
        // TODO Auto-generated method stub

    }

}
