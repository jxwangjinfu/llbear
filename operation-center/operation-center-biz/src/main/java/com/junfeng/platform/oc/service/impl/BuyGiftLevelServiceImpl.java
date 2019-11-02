package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.BuyGiftLevel;
import com.junfeng.platform.oc.service.BuyGiftLevelService;
import com.junfeng.platform.oc.mapper.BuyGiftLevelMapper;
import org.springframework.stereotype.Service;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-15 14:32:05
 */
@Service("buyGiftLevelService")
public class BuyGiftLevelServiceImpl extends ServiceImpl<BuyGiftLevelMapper, BuyGiftLevel>
        implements BuyGiftLevelService {

    /**
     * 买赠等级表简单分页查询
     *
     * @param buyGiftLevel
     *            买赠等级表
     * @return
     */
    @Override
    public IPage<BuyGiftLevel> getBuyGiftLevelPage(Page<BuyGiftLevel> page, BuyGiftLevel buyGiftLevel) {
        return baseMapper.getBuyGiftLevelPage(page, buyGiftLevel);
    }

    /**
     * 获取买赠等级
     *
     * @param orderVO
     * @return com.junfeng.platform.oc.entity.BuyGiftLevel
     * @author: wangjf
     * @createTime: 2019/10/18 14:42
     */
    @Override
    public BuyGiftLevel getBuyGiftLevel(OrderVO orderVO) {
        return baseMapper.getBuyGiftLevel(orderVO);
    }

}
