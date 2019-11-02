package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.BuyGiftMember;
import org.apache.ibatis.annotations.Param;

/**
 * 买赠会员表
 *
 * @author wangjf
 * @date 2019-10-14 15:10:06
 */
public interface BuyGiftMemberMapper extends BaseMapper<BuyGiftMember> {
  /**
    * 买赠会员表简单分页查询
    * @param buyGiftMember 买赠会员表
    * @return
    */
  IPage<BuyGiftMember> getBuyGiftMemberPage(Page page, @Param("buyGiftMember") BuyGiftMember buyGiftMember);


}
