package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 商户信息
 *
 * @author wangk
 * @date 2019-09-19 10:18:01
 */
public interface PaymentMchInfoMapper extends BaseMapper<PaymentMchInfo> {
  /**
    * 商户信息简单分页查询
    * @param paymentMchInfo 商户信息
    * @return
    */
  IPage<PaymentMchInfo> getPaymentMchInfoPage(Page page, @Param("paymentMchInfo") PaymentMchInfo paymentMchInfo);


}
