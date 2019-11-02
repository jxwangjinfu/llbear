package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.UserGroup;

/**
 * 客服组信息
 *
 * @author lw
 * @date 2019-10-12 11:44:30
 */
public interface UserGroupService extends IService<UserGroup> {

  /**
   * 客服组信息简单分页查询
   * @param userGroup 客服组信息
   * @return
   */
  IPage<UserGroup> getUserGroupPage(Page<UserGroup> page, UserGroup userGroup);

  /**
   * 功能描述: 保存并验证
   * @author: lw
   * @createTime: 2019/10/31 9:43
   * @param entity
   * @return boolean
   */
	boolean saveWithCheck(UserGroup entity);
}
