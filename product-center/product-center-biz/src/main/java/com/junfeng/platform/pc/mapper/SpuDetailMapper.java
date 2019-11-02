package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.SpuDetail;

/**
 * spu详细信息
 *
 * @author lw
 * @date 2019-10-14 15:43:56
 */
public interface SpuDetailMapper extends BaseMapper<SpuDetail> {
  /**
    * spu详细信息简单分页查询
    * @param spuDetail spu详细信息
    * @return
    */
  IPage<SpuDetail> getSpuDetailPage(Page page, @Param("spuDetail") SpuDetail spuDetail);


}
