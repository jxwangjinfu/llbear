package com.junfeng.wc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.wc.entity.Miniapp;
import org.apache.ibatis.annotations.Param;

/**
 * 小程序
 *
 * @author daiysh
 * @date 2019-09-25 10:49:30
 */
public interface MiniappMapper extends BaseMapper<Miniapp> {
  /**
    * 小程序简单分页查询
    * @param miniapp 小程序
    * @return
    */
  IPage<Miniapp> getMiniappPage(Page page, @Param("miniapp") Miniapp miniapp);


}
