package com.junfeng.platform.mc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.mc.api.entity.MemberAddress;
import com.junfeng.platform.mc.service.MemberAddressService;
import com.junfeng.platform.mc.mapper.MemberAddressMapper;
import org.springframework.stereotype.Service;

/**
 * 会员地址
 *
 * @author daiysh
 * @date 2019-09-23 09:32:24
 */
@Service("memberAddressService")
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressService {

  /**
   * 会员地址简单分页查询
   * @param memberAddress 会员地址
   * @return
   */
  @Override
  public IPage<MemberAddress> getMemberAddressPage(Page<MemberAddress> page, MemberAddress memberAddress){
      return baseMapper.getMemberAddressPage(page,memberAddress);
  }

}
