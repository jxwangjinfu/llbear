package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Category;

/**
 * 类目表
 *
 * @author lw
 * @date 2019-10-14 15:19:07
 */
public interface CategoryMapper extends BaseMapper<Category> {
  /**
    * 类目表简单分页查询
    * @param category 类目表
    * @return
    */
  IPage<Category> getCategoryPage(Page page, @Param("category") Category category);


}
