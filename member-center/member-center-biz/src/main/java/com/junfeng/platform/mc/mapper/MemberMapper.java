package com.junfeng.platform.mc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * 会员表
 *
 * @author daiysh
 * @date 2019-09-23 09:22:11
 */
public interface MemberMapper extends BaseMapper<Member> {
  /**
    * 会员表简单分页查询
    * @param member 会员表
    * @return
    */
  IPage<Member> getMemberPage(Page page, @Param("member") Member member);


}
