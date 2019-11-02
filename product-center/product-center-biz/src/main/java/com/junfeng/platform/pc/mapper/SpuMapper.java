package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Spu;

/**
 * spu信息
 *
 * @author lw
 * @date 2019-10-21 11:24:11
 */
public interface SpuMapper extends BaseMapper<Spu> {
  /**
    * spu信息简单分页查询
    * @param spu spu信息
    * @return
    */
  IPage<Spu> getSpuPage(Page page, @Param("spu") Spu spu);
}
