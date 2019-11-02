package com.junfeng.platform.oc.service;

import com.junfeng.platform.oc.api.result.OrderResult;
import com.junfeng.platform.oc.api.vo.OrderVO;

public interface MemberOrderService {

    /**
     * 订单预览
     *
     * @author: wangjf
     * @createTime: 2019/10/16 17:43
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     */
    OrderResult orderPreview(OrderVO orderVO);

    /**
     * 订单计算
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:23
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     */
    OrderResult orderCalculate(OrderVO orderVO);

    /**
     * 优惠锁定
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:17
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     */
    OrderResult orderLock(OrderVO orderVO);

    /**
     * 销券和销红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:18
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean orderComplete(Long memberId, String orderNo);

    /**
     * 订单取消，返回优惠券和红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:18
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean orderCancel(Long memberId, String orderNo);

    /**
     * 订单买赠
     *
     * @author: wangjf
     * @createTime: 2019/10/18 14:37
     * @param orderVO
     * @return java.lang.Boolean
     */
    Boolean orderBuyGift(OrderVO orderVO);

}
