package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.GiftMember;
import org.apache.ibatis.annotations.Param;

/**
 * 赠品会员表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:37
 */
public interface GiftMemberMapper extends BaseMapper<GiftMember> {
  /**
    * 赠品会员表简单分页查询
    * @param giftMember 赠品会员表
    * @return
    */
  IPage<GiftMember> getGiftMemberPage(Page page, @Param("giftMember") GiftMember giftMember);


}
