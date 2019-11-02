package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentMchChannelRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 商户_支付通道关联表
 *
 * @author wangk
 * @date 2019-09-25 15:30:24
 */
public interface PaymentMchChannelRelationMapper extends BaseMapper<PaymentMchChannelRelation> {
  /**
    * 商户_支付通道关联表简单分页查询
    * @param paymentMchChannelRelation 商户_支付通道关联表
    * @return
    */
  IPage<PaymentMchChannelRelation> getPaymentMchChannelRelationPage(Page page, @Param("paymentMchChannelRelation") PaymentMchChannelRelation paymentMchChannelRelation);


}
