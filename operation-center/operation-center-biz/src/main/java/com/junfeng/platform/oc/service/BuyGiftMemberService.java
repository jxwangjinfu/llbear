package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.BuyGiftMember;

/**
 * 买赠会员表
 *
 * @author wangjf
 * @date 2019-10-14 15:10:06
 */
public interface BuyGiftMemberService extends IService<BuyGiftMember> {

  /**
   * 买赠会员表简单分页查询
   * @param buyGiftMember 买赠会员表
   * @return
   */
  IPage<BuyGiftMember> getBuyGiftMemberPage(Page<BuyGiftMember> page, BuyGiftMember buyGiftMember);


}
