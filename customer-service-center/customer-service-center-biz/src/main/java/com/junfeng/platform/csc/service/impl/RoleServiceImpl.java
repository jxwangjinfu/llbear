package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.Role;
import com.junfeng.platform.csc.mapper.RoleMapper;
import com.junfeng.platform.csc.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 客服权限
 *
 * @author lw
 * @date 2019-10-24 15:11:57
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  /**
   * 客服权限简单分页查询
   * @param role 客服权限
   * @return
   */
  @Override
  public IPage<Role> getRolePage(Page<Role> page, Role role){
      return baseMapper.getRolePage(page,role);
  }

}
