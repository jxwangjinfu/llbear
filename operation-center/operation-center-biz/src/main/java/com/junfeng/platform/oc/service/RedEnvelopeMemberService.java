package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO;

import java.util.List;

/**
 * 红包会员表
 *
 * @author wangjf
 * @date 2019-10-09 16:37:15
 */
public interface RedEnvelopeMemberService extends IService<RedEnvelopeMember> {

    /**
     * 红包会员表简单分页查询
     *
     * @param redEnvelopeMember
     *            红包会员表
     * @return
     */
    IPage<RedEnvelopeMember> getRedEnvelopeMemberPage(Page<RedEnvelopeMember> page,
            RedEnvelopeMember redEnvelopeMember);

    /**
     * 发送至MQ
     *
     * @author: wangjf
     * @createTime: 2019/10/9 16:59
     * @param list
     * @return void
     */
    void sendRedEnvelopeGenerateToMq(List<RedEnvelopeGenerateMessageVO> list);

    /**
     * 获取红包
     *
     * @author: wangjf
     * @createTime: 2019/10/10 13:59
     * @param memberId
     * @param redEnvelopeId
     * @return java.lang.String
     */
    String updateRedEnvelope(Long memberId, Long redEnvelopeId);

    /**
     * 获取会员的可用红包
     *
     * @author: wangjf
     * @createTime: 2019/10/16 16:37
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO>
     */
    List<RedEnvelopeMemberDTO> getRedEnvelopeList(OrderVO orderVO);

    /**
     * 销售销红包
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:56
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean updateRedEnvelopeMember(List<Long> idList, String orderNo);

    /**
     * 锁定红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:28
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean lockRedEnvelopeMember(List<Long> idList, String orderNo);

    /**
     * 销红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:35
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean completeEnvelopeMember(Long memberId, String orderNo);

    /**
     * 退还红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:35
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean cancelEnvelopeMember(Long memberId, String orderNo);

}
