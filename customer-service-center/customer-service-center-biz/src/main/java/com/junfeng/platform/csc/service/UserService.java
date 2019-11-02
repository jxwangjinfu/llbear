package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.User;

/**
 * 客服人员信息
 *
 * @author lw
 * @date 2019-10-12 11:21:31
 */
public interface UserService extends IService<User> {

  /**
   * 客服人员信息简单分页查询
   * @param user 客服人员信息
   * @return
   */
  IPage<User> getUserPage(Page<User> page, User user);

  User getUserByUserCode(Integer userCode);
  /**
   * 功能描述: 保存并验证
   * @author: lw
   * @createTime: 2019/10/31 9:44
   * @param user
   * @return boolean
   */
  boolean saveWithCheck(User user);
}
