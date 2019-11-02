package com.junfeng.wc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.wc.entity.Mp;
import org.apache.ibatis.annotations.Param;

/**
 * 公众号
 *
 * @author daiysh
 * @date 2019-09-25 10:53:00
 */
public interface MpMapper extends BaseMapper<Mp> {
  /**
    * 公众号简单分页查询
    * @param mp 公众号
    * @return
    */
  IPage<Mp> getMpPage(Page page, @Param("mp") Mp mp);


}
