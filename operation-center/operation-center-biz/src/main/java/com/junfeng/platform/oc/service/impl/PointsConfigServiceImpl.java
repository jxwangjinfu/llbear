package com.junfeng.platform.oc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.result.PointsResult;
import com.junfeng.platform.oc.api.vo.PointsVO;
import com.junfeng.platform.oc.entity.PointsConfig;
import com.junfeng.platform.oc.mapper.PointsConfigMapper;
import com.junfeng.platform.oc.service.PointsConfigService;
import org.springframework.stereotype.Service;

/**
 * 运营规则表
 *
 * @author wangjf
 * @date 2019-10-17 17:14:05
 */
@Service("pointsConfigService")
public class PointsConfigServiceImpl extends ServiceImpl<PointsConfigMapper, PointsConfig>
        implements PointsConfigService {

    /**
     * 运营规则表简单分页查询
     *
     * @param pointsConfig
     *            运营规则表
     * @return
     */
    @Override
    public IPage<PointsConfig> getPointsConfigPage(Page<PointsConfig> page, PointsConfig pointsConfig) {
        return baseMapper.getPointsConfigPage(page, pointsConfig);
    }

    /**
     * 获取PointResult
     *
     * @param pointVO
     * @return com.junfeng.platform.oc.api.result.PointResult
     * @author: wangjf
     * @createTime: 2019/10/17 17:26
     */
    @Override
    public PointsResult getPointsConfig(PointsVO pointVO) {
        PointsResult pointResult = new PointsResult();
        PointsConfig one = getOne(Wrappers.<PointsConfig> query().lambda().eq(PointsConfig::getState, 0)
                .eq(PointsConfig::getClientId, pointVO.getClientId())
                .eq(PointsConfig::getPointsType, pointVO.getPointsType()));
        if (one == null) {
            return pointResult;
        }
        BeanUtil.copyProperties(one, pointResult);
        return pointResult;
    }

}
