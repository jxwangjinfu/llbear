package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.Role;

/**
 * 客服权限
 *
 * @author lw
 * @date 2019-10-24 15:11:57
 */
public interface RoleService extends IService<Role> {

  /**
   * 客服权限简单分页查询
   * @param role 客服权限
   * @return
   */
  IPage<Role> getRolePage(Page<Role> page, Role role);


}
