package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.BuyGiftMember;
import com.junfeng.platform.oc.service.BuyGiftMemberService;
import com.junfeng.platform.oc.mapper.BuyGiftMemberMapper;
import org.springframework.stereotype.Service;

/**
 * 买赠会员表
 *
 * @author wangjf
 * @date 2019-10-14 15:10:06
 */
@Service("buyGiftMemberService")
public class BuyGiftMemberServiceImpl extends ServiceImpl<BuyGiftMemberMapper, BuyGiftMember> implements BuyGiftMemberService {

  /**
   * 买赠会员表简单分页查询
   * @param buyGiftMember 买赠会员表
   * @return
   */
  @Override
  public IPage<BuyGiftMember> getBuyGiftMemberPage(Page<BuyGiftMember> page, BuyGiftMember buyGiftMember){
      return baseMapper.getBuyGiftMemberPage(page,buyGiftMember);
  }

}
