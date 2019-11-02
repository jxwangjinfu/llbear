package com.junfeng.platform.mc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.mc.api.entity.MemberAddress;

/**
 * 会员地址
 *
 * @author daiysh
 * @date 2019-09-23 09:32:24
 */
public interface MemberAddressService extends IService<MemberAddress> {

  /**
   * 会员地址简单分页查询
   * @param memberAddress 会员地址
   * @return
   */
  IPage<MemberAddress> getMemberAddressPage(Page<MemberAddress> page, MemberAddress memberAddress);


}
