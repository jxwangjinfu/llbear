package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.PointsMemberFlow;
import com.junfeng.platform.oc.service.PointsMemberFlowService;
import com.junfeng.platform.oc.mapper.PointsMemberFlowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员获取积分流水
 *
 * @author wangjf
 * @date 2019-10-21 14:55:59
 */
@Service("pointsMemberFlowService")
public class PointsMemberFlowServiceImpl extends ServiceImpl<PointsMemberFlowMapper, PointsMemberFlow>
        implements PointsMemberFlowService {

    /**
     * 会员获取积分流水简单分页查询
     *
     * @param pointsMemberFlow
     *            会员获取积分流水
     * @return
     */
    @Override
    public IPage<PointsMemberFlow> getPointsMemberFlowPage(Page<PointsMemberFlow> page,
            PointsMemberFlow pointsMemberFlow) {
        return baseMapper.getPointsMemberFlowPage(page, pointsMemberFlow);
    }

    /**
     * 查询指定日期的记录
     *
     * @param clientId
     * @param mcMemberId
     * @param startTime
     * @param endTime
     * @return java.util.List<com.junfeng.platform.oc.entity.PointsMemberFlow>
     * @author: wangjf
     * @createTime: 2019/10/21 15:44
     */
    @Override
    public List<PointsMemberFlow> getPointsMemberFlowList(String clientId, Long mcMemberId, String startTime,
            String endTime) {
        return baseMapper.getPointsMemberFlowList(clientId, mcMemberId, startTime, endTime);
    }

}
