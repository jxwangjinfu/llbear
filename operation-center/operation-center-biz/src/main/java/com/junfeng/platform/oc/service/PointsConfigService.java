package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.result.PointsResult;
import com.junfeng.platform.oc.api.vo.PointsVO;
import com.junfeng.platform.oc.entity.PointsConfig;

/**
 * 运营规则表
 *
 * @author wangjf
 * @date 2019-10-17 17:14:05
 */
public interface PointsConfigService extends IService<PointsConfig> {

    /**
     * 运营规则表简单分页查询
     *
     * @param pointsConfig
     *            运营规则表
     * @return
     */
    IPage<PointsConfig> getPointsConfigPage(Page<PointsConfig> page, PointsConfig pointsConfig);

    /**
     * 获取PointResult
     * @author: wangjf
     * @createTime: 2019/10/17 17:26
     * @param pointVO
     * @return com.junfeng.platform.oc.api.result.PointResult
     */
	PointsResult getPointsConfig(PointsVO pointVO);

}
