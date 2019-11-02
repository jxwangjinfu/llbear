package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.PointsConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 运营规则表
 *
 * @author wangjf
 * @date 2019-10-17 17:14:05
 */
public interface PointsConfigMapper extends BaseMapper<PointsConfig> {
  /**
    * 运营规则表简单分页查询
    * @param pointsConfig 运营规则表
    * @return
    */
  IPage<PointsConfig> getPointsConfigPage(Page page, @Param("pointsConfig") PointsConfig pointsConfig);


}
