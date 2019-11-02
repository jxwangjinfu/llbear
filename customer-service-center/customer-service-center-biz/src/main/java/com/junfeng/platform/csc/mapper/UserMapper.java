package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 客服人员信息
 *
 * @author lw
 * @date 2019-10-24 15:25:15
 */
public interface UserMapper extends BaseMapper<User> {
  /**
    * 客服人员信息简单分页查询
    * @param user 客服人员信息
    * @return
    */
  IPage<User> getUserPage(Page page, @Param("user") User user);


}
