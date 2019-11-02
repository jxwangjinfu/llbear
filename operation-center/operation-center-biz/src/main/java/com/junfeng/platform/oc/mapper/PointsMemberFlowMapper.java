package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.PointsMemberFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员获取积分流水
 *
 * @author wangjf
 * @date 2019-10-21 14:55:59
 */
public interface PointsMemberFlowMapper extends BaseMapper<PointsMemberFlow> {
    /**
     * 会员获取积分流水简单分页查询
     *
     * @param pointsMemberFlow
     *            会员获取积分流水
     * @return
     */
    IPage<PointsMemberFlow> getPointsMemberFlowPage(Page page,
            @Param("pointsMemberFlow") PointsMemberFlow pointsMemberFlow);

    /**
     * 查询指定日期的记录
     * @author: wangjf
     * @createTime: 2019/10/21 15:43
     * @param clientId
     * @param mcMemberId
     * @param startTime
     * @param endTime
     * @return java.util.List<com.junfeng.platform.oc.entity.PointsMemberFlow>
     */
    List<PointsMemberFlow> getPointsMemberFlowList(@Param("clientId") String clientId,
            @Param("mcMemberId") Long mcMemberId, @Param("startTime") String startTime,
            @Param("endTime") String endTime);

}
