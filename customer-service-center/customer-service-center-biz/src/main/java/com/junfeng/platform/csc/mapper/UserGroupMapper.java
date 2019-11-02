package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.UserGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 客服组信息
 *
 * @author lw
 * @date 2019-10-12 11:44:30
 */
public interface UserGroupMapper extends BaseMapper<UserGroup> {
  /**
    * 客服组信息简单分页查询
    * @param userGroup 客服组信息
    * @return
    */
  IPage<UserGroup> getUserGroupPage(Page page, @Param("userGroup") UserGroup userGroup);


}
