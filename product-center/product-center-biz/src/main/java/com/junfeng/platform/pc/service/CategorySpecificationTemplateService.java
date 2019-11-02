package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.CategorySpecificationTemplate;

/**
 * 类目关联模板
 *
 * @author lw
 * @date 2019-10-14 15:19:25
 */
public interface CategorySpecificationTemplateService extends IService<CategorySpecificationTemplate> {

  /**
   * 类目关联模板简单分页查询
   * @param categorySpecificationTemplate 类目关联模板
   * @return
   */
  IPage<CategorySpecificationTemplate> getCategorySpecificationTemplatePage(Page<CategorySpecificationTemplate> page, CategorySpecificationTemplate categorySpecificationTemplate);


}
