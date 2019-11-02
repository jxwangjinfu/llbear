package com.junfeng.platform.mc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.entity.MemberPointsFlow;
import org.apache.ibatis.annotations.Param;

/**
 * 会员积分流水表
 *
 * @author daiysh
 * @date 2019-10-08 15:03:36
 */
public interface MemberPointsFlowMapper extends BaseMapper<MemberPointsFlow> {
  /**
    * 会员积分流水表简单分页查询
    * @param memberPointsFlow 会员积分流水表
    * @return
    */
  IPage<MemberPointsFlow> getMemberPointsFlowPage(Page page, @Param("memberPointsFlow") MemberPointsFlow memberPointsFlow);


}
