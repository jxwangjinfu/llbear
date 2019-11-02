package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.Category;

/**
 * 类目表
 *
 * @author lw
 * @date 2019-10-14 15:19:07
 */
public interface CategoryService extends IService<Category> {

  /**
   * 类目表简单分页查询
   * @param category 类目表
   * @return
   */
  IPage<Category> getCategoryPage(Page<Category> page, Category category);


}
