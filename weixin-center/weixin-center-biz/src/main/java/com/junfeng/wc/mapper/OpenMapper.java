package com.junfeng.wc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.wc.entity.Open;
import org.apache.ibatis.annotations.Param;

/**
 * 开放平台
 *
 * @author daiysh
 * @date 2019-09-25 10:55:42
 */
public interface OpenMapper extends BaseMapper<Open> {
  /**
    * 开放平台简单分页查询
    * @param open 开放平台
    * @return
    */
  IPage<Open> getOpenPage(Page page, @Param("open") Open open);


}
