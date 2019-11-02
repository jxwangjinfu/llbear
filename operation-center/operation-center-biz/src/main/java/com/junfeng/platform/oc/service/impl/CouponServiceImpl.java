package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO;
import com.junfeng.platform.oc.constant.OperationCenterConstant;
import com.junfeng.platform.oc.entity.Coupon;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.mapper.CouponMapper;
import com.junfeng.platform.oc.mapper.CouponMemberMapper;
import com.junfeng.platform.oc.service.CouponMemberService;
import com.junfeng.platform.oc.service.CouponService;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.junfeng.platform.oc.util.ContextHolderUtil;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券
 *
 * @author wangjf
 * @date 2019-09-23 15:22:22
 */
@Service
@AllArgsConstructor
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    private final CouponMemberService couponMemberService;
    private final CouponMemberMapper couponMemberMapper;
    private final QuartzLogService quartzLogService;

    /**
     * 优惠券简单分页查询
     *
     * @param coupon
     *            优惠券
     * @return
     */
    @Override
    public IPage<Coupon> getCouponPage(Page<Coupon> page, Coupon coupon) {
        return baseMapper.getCouponPage(page, coupon);
    }

    /**
     * 保存优惠券
     *
     * @param coupon
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午4:08:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveCoupon(Coupon coupon) {

        save(coupon);
        /**
         * 优惠券启动VO
         */
        quartzLogService.sendQcQuartz(coupon.getId(), QuartzTypeEnum.COUPON.getCode(), 1, coupon.getUseStartTime());
        /**
         * 优惠券关闭VO
         */
        quartzLogService.sendQcQuartz(coupon.getId(), QuartzTypeEnum.COUPON.getCode(), -1, coupon.getUseEndTime());

        List<CouponGenerateMessageVO> list = getCouponGenerateMessageVO(coupon.getId(), coupon.getPublishNumber());
        couponMemberService.sendCouponGenerateToMq(list);

        return Boolean.TRUE;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 16:21
     */
    @Override
    public Boolean updateState(Long id, Integer state) {

        Coupon coupon = getById(id);
        if (coupon == null) {
            return Boolean.FALSE;
        }

        Coupon updateObj = new Coupon();

        updateObj.setId(coupon.getId());
        updateObj.setState(state);
        baseMapper.updateById(updateObj);

        CouponMember couponMember = new CouponMember();
        couponMember.setState(state);

        couponMemberMapper.update(couponMember,
                Wrappers.<CouponMember> query().lambda().eq(CouponMember::getOcCouponId, coupon.getId()));

        return Boolean.TRUE;
    }

    /**
     * 获取分段生成优惠券信息
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:16
     * @param couponId
     * @param publishNumber
     * @return java.util.List<com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO>
     */
    public List<CouponGenerateMessageVO> getCouponGenerateMessageVO(long couponId, int publishNumber) {

        List<CouponGenerateMessageVO> list = Lists.newArrayList();

        if (publishNumber > OperationCenterConstant.COUPON_GENERATE_PART_SIZE) {
            // 需要分离的次数
            int length = publishNumber / OperationCenterConstant.COUPON_GENERATE_PART_SIZE;

            for (int i = 0; i < length; i++) {
                CouponGenerateMessageVO couponGenerateMessageVO = new CouponGenerateMessageVO();
                couponGenerateMessageVO.setStartNo(i * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                couponGenerateMessageVO.setEndNo(i * (OperationCenterConstant.COUPON_GENERATE_PART_SIZE));
                couponGenerateMessageVO.setOcCouponId(couponId);
                couponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(couponGenerateMessageVO);
            }

            // 分离次数后还剩余多少，如果大于0则需要多分离一次
            int flag = publishNumber % OperationCenterConstant.COUPON_GENERATE_PART_SIZE;
            if (flag > 0) {
                CouponGenerateMessageVO flagCouponGenerateMessageVO = new CouponGenerateMessageVO();
                flagCouponGenerateMessageVO.setStartNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                flagCouponGenerateMessageVO.setEndNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + flag);
                flagCouponGenerateMessageVO.setOcCouponId(couponId);
                flagCouponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(flagCouponGenerateMessageVO);
            }

        } else {
            CouponGenerateMessageVO couponGenerateMessageVO = new CouponGenerateMessageVO();
            couponGenerateMessageVO.setStartNo(1);
            couponGenerateMessageVO.setEndNo(publishNumber);
            couponGenerateMessageVO.setOcCouponId(couponId);
            couponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
            list.add(couponGenerateMessageVO);
        }

        return list;

    }

}
