package com.junfeng.platform.oc.api.feign.fallback;

import com.junfeng.platform.oc.api.result.GroupBuyResult;
import com.junfeng.platform.oc.api.result.OrderResult;
import com.junfeng.platform.oc.api.result.PointsResult;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.api.vo.PointsVO;
import com.junfeng.platform.oc.api.vo.SignInVO;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.stereotype.Component;

import com.junfeng.platform.oc.api.feign.OcRemoteService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @projectName:pig
 * @author:Wangjf
 * @date:2019/10/14 17:22
 * @version 1.0
 */
@Slf4j
@Component
public class OcRemoteServiceFallbackImpl implements OcRemoteService {
    @Setter
    private Throwable cause;

    /**
     * 订单预览
     *
     * @param vo
     * @return com.junfeng.platform.oc.api.result.OrderResult
     * @author: wangjf
     * @createTime: 2019/10/14 17:34
     */
    @Override
    public R<OrderResult> orderPreview(OrderVO vo, String from) {
        log.error("feign 调用订单预览失败", cause);
        log.error("订单信息={}", vo);
        return R.feignFailed("失败");
    }

    /**
     * 订单优惠计算
     *
     * @param vo
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     * @author: wangjf
     * @createTime: 2019/10/17 14:20
     */
    @Override
    public R<OrderResult> orderCalculate(OrderVO vo, String from) {
        log.error("feign 调用订单计算失败", cause);
        log.error("订单信息={}", vo);
        return R.feignFailed("失败");
    }

    /**
     * 订单锁定优惠
     *
     * @param vo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     * @author: wangjf
     * @createTime: 2019/10/18 10:55
     */
    @Override
    public R<OrderResult> orderLock(OrderVO vo, String from) {
        return R.feignFailed("失败");
    }

    /**
     * 订单销券和销红包
     *
     * @param orderNo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     * @author: wangjf
     * @createTime: 2019/10/18 10:58
     */
    @Override
    public R<Boolean> orderComplete(Long memberId, String orderNo, String from) {
        return R.feignFailed("失败");
    }

    /**
     * 订单取消，返还优惠券和红包
     *
     * @param orderNo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     * @author: wangjf
     * @createTime: 2019/10/18 11:00
     */
    @Override
    public R<Boolean> orderCancel(Long memberId, String orderNo, String from) {
        return R.feignFailed("失败");
    }

    /**
     * 积分计算
     *
     * @param vo
     * @param from
     * @return com.junfeng.platform.oc.api.result.PointResult
     * @author: wangjf
     * @createTime: 2019/10/14 17:34
     */
    @Override
    public R<PointsResult> getPointResult(PointsVO vo, String from) {
        log.error("feign 调用规则失败", cause);
        log.error("规则信息信息={}", vo);
        return R.feignFailed("失败");
    }

    /**
     * 会员积分计算接口
     *
     * @param vo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     * @author: wangjf
     * @createTime: 2019/10/18 14:07
     */
    @Override
    public R<Boolean> memberPointCalculate(PointsVO vo, String from) {
        return R.feignFailed("失败");
    }

    /**
     * 买赠
     *
     * @param orderVO
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     * @author: wangjf
     * @createTime: 2019/10/18 14:23
     */
    @Override
    public R<Boolean> orderBuyGift(OrderVO orderVO, String from) {
        return R.feignFailed("失败");
    }

    /**
     * 根据SKU_ID获取团购活动
     *
     * @param id
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.GroupBuyResult>
     * @author: wangjf
     * @createTime: 2019/10/22 14:29
     */
    @Override
    public R<GroupBuyResult> groupbuySkuId(Long id, String from) {
        return R.feignFailed("失败");
    }

	/**
	 * 会员登录获取积分
	 *
	 * @param signInVO
	 * @param from
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Integer>
	 * @author: wangjf
	 * @createTime: 2019/10/23 14:06
	 */
	@Override
	public R<Integer> memberSignInPoint(SignInVO signInVO, String from) {
		return R.feignFailed("失败");
	}


}
