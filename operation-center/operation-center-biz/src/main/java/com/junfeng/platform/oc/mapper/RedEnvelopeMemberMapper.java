package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 红包会员表
 *
 * @author wangjf
 * @date 2019-10-09 16:37:15
 */
public interface RedEnvelopeMemberMapper extends BaseMapper<RedEnvelopeMember> {
    /**
     * 红包会员表简单分页查询
     *
     * @param redEnvelopeMember
     *            红包会员表
     * @return
     */
    IPage<RedEnvelopeMember> getRedEnvelopeMemberPage(Page page,
            @Param("redEnvelopeMember") RedEnvelopeMember redEnvelopeMember);

    /**
     * 获取会员红包
     * @author: wangjf
     * @createTime: 2019/10/16 16:29
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO>
     */
    List<RedEnvelopeMemberDTO> getRedEnvelopeList(@Param("orderVO") OrderVO orderVO);

}
