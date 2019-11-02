package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.CategorySpecificationTemplate;

/**
 * 类目关联模板
 *
 * @author lw
 * @date 2019-10-14 15:19:25
 */
public interface CategorySpecificationTemplateMapper extends BaseMapper<CategorySpecificationTemplate> {
  /**
    * 类目关联模板简单分页查询
    * @param categorySpecificationTemplate 类目关联模板
    * @return
    */
  IPage<CategorySpecificationTemplate> getCategorySpecificationTemplatePage(Page page, @Param("categorySpecificationTemplate") CategorySpecificationTemplate categorySpecificationTemplate);


}
