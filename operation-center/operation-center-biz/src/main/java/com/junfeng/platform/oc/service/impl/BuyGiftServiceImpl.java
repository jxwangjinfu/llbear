package com.junfeng.platform.oc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.vo.BuyGiftLevelGoodsVO;
import com.junfeng.platform.oc.api.vo.BuyGiftLevelVO;
import com.junfeng.platform.oc.api.vo.BuyGiftVO;
import com.junfeng.platform.oc.entity.BuyGift;
import com.junfeng.platform.oc.entity.BuyGiftLevel;
import com.junfeng.platform.oc.entity.BuyGiftLevelGoods;
import com.junfeng.platform.oc.mapper.BuyGiftMapper;
import com.junfeng.platform.oc.service.BuyGiftLevelGoodsService;
import com.junfeng.platform.oc.service.BuyGiftLevelService;
import com.junfeng.platform.oc.service.BuyGiftService;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 买赠表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:35
 */
@Service
public class BuyGiftServiceImpl extends ServiceImpl<BuyGiftMapper, BuyGift> implements BuyGiftService {

    @Autowired
    private QuartzLogService quartzLogService;
    @Autowired
    private BuyGiftLevelService buyGiftLevelService;
    @Autowired
    private BuyGiftLevelGoodsService buyGiftLevelGoodsService;

    /**
     * 买赠表简单分页查询
     *
     * @param buyGift
     *            买赠表
     * @return
     */
    @Override
    public IPage<BuyGift> getBuyGiftPage(Page<BuyGift> page, BuyGift buyGift) {
        return baseMapper.getBuyGiftPage(page, buyGift);
    }

    /**
     * 修改状态
     *
     * @author: wangjf
     * @createTime: 2019/10/15 16:12
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    @Override
    public Boolean updateState(Long id, Integer state) {

        BuyGift buyGift = getById(id);
        if (buyGift == null) {
            return Boolean.FALSE;
        }

        BuyGift updateObj = new BuyGift();
        updateObj.setId(id);
        updateObj.setState(state);
        baseMapper.updateById(updateObj);
        return Boolean.TRUE;
    }

    /**
     * 保存买赠
     *
     * @param buyGift
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 16:58
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBuyGift(BuyGift buyGift) {
        save(buyGift);

        quartzLogService.sendQcQuartz(buyGift.getId(), QuartzTypeEnum.BUY.getCode(), 1, buyGift.getUseStartTime());
        quartzLogService.sendQcQuartz(buyGift.getId(), QuartzTypeEnum.BUY.getCode(), -1, buyGift.getUseEndTime());
        return Boolean.TRUE;
    }

    /**
     * 保存买赠
     *
     * @param buyGiftVO
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/25 14:33
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBuyGiftVO(BuyGiftVO buyGiftVO) {

        BuyGift buyGift = new BuyGift();
        BeanUtil.copyProperties(buyGiftVO, buyGift);
        save(buyGift);

        quartzLogService.sendQcQuartz(buyGift.getId(), QuartzTypeEnum.BUY.getCode(), 1, buyGift.getUseStartTime());
        quartzLogService.sendQcQuartz(buyGift.getId(), QuartzTypeEnum.BUY.getCode(), -1, buyGift.getUseEndTime());

        saveBuyGiftLevelList(buyGift.getId(), buyGiftVO.getLevelList());
        return Boolean.TRUE;
    }

    /**
     * 保存等级
     * @author: wangjf
     * @createTime: 2019/10/25 14:52
     * @param buyGiftId
     * @param levelList
     * @return void
     */
    private void saveBuyGiftLevelList(Long buyGiftId, List<BuyGiftLevelVO> levelList) {
        if (CollectionUtils.isEmpty(levelList)) {
            return;
        }
        for (BuyGiftLevelVO buyGiftLevelVO : levelList) {

            BuyGiftLevel buyGiftLevel = new BuyGiftLevel();
            BeanUtil.copyProperties(buyGiftLevelVO, buyGiftLevel);
            buyGiftLevel.setOcBuyGiftId(buyGiftId);
            buyGiftLevelService.save(buyGiftLevel);
            saveBuyGiftLevelGoodsList(buyGiftLevel.getId(), buyGiftLevelVO.getGoodsList());
        }

    }

    /**
     * 保存等级对应的商品
     * @author: wangjf
     * @createTime: 2019/10/25 14:52
     * @param buyGiftLevelId
     * @param goodsList
     * @return void
     */
    private void saveBuyGiftLevelGoodsList(Long buyGiftLevelId, List<BuyGiftLevelGoodsVO> goodsList) {
        if (CollectionUtils.isEmpty(goodsList)) {
            return;
        }
        for (BuyGiftLevelGoodsVO buyGiftLevelGoodsVO : goodsList) {
            BuyGiftLevelGoods buyGiftLevelGoods = new BuyGiftLevelGoods();
            BeanUtil.copyProperties(buyGiftLevelGoodsVO, buyGiftLevelGoods);
            buyGiftLevelGoods.setOcBuyGiftLevelId(buyGiftLevelId);
            buyGiftLevelGoodsService.save(buyGiftLevelGoods);
        }
    }

}
