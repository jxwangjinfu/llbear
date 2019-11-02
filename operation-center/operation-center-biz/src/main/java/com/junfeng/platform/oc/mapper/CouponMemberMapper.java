package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.entity.CouponMemberDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券会员表
 *
 * @author wangjf
 * @date 2019-09-29 16:51:58
 */
public interface CouponMemberMapper extends BaseMapper<CouponMember> {
    /**
     * 优惠券会员表简单分页查询
     *
     * @param couponMember
     *            优惠券会员表
     * @return
     */
    IPage<CouponMember> getCouponMemberPage(Page page, @Param("couponMember") CouponMember couponMember);

    /**
     * 通过订单获取会员优惠券
     *
     * @author: wangjf
     * @createTime: 2019/10/16 15:20
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.CouponMemberDTO>
     */
    List<CouponMemberDTO> getCouponList(@Param("orderVO") OrderVO orderVO);

}
