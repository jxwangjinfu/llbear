package com.junfeng.platform.mc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.entity.MemberAddress;
import org.apache.ibatis.annotations.Param;

/**
 * 会员地址
 *
 * @author daiysh
 * @date 2019-09-23 09:32:24
 */
public interface MemberAddressMapper extends BaseMapper<MemberAddress> {
  /**
    * 会员地址简单分页查询
    * @param memberAddress 会员地址
    * @return
    */
  IPage<MemberAddress> getMemberAddressPage(Page page, @Param("memberAddress") MemberAddress memberAddress);


}
