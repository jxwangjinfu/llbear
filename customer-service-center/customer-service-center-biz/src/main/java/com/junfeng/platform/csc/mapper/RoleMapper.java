package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * 客服权限
 *
 * @author lw
 * @date 2019-10-24 15:11:57
 */
public interface RoleMapper extends BaseMapper<Role> {
  /**
    * 客服权限简单分页查询
    * @param role 客服权限
    * @return
    */
  IPage<Role> getRolePage(Page page, @Param("role") Role role);


}
