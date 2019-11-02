package com.junfeng.platform.oc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.result.GroupBuyResult;
import com.junfeng.platform.oc.entity.GroupBuy;
import com.junfeng.platform.oc.mapper.GroupBuyMapper;
import com.junfeng.platform.oc.service.GroupBuyService;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 团购活动表
 *
 * @author wangjf
 * @date 2019-10-22 14:05:08
 */
@Service("groupBuyService")
public class GroupBuyServiceImpl extends ServiceImpl<GroupBuyMapper, GroupBuy> implements GroupBuyService {

    @Autowired
    private QuartzLogService quartzLogService;

    /**
     * 团购活动表简单分页查询
     *
     * @param groupBuy
     *            团购活动表
     * @return
     */
    @Override
    public IPage<GroupBuy> getGroupBuyPage(Page<GroupBuy> page, GroupBuy groupBuy) {
        return baseMapper.getGroupBuyPage(page, groupBuy);
    }

    /**
     * 通过SKU_ID获取拼团设置
     *
     * @param skuId
     * @return com.junfeng.platform.oc.entity.GroupBuy
     * @author: wangjf
     * @createTime: 2019/10/22 14:19
     */
    @Override
    public GroupBuyResult getBySkuId(Long skuId) {
        GroupBuyResult groupBuyResult = new GroupBuyResult();
        GroupBuy one = getOne(
                Wrappers.<GroupBuy> query().lambda().eq(GroupBuy::getState, 1).eq(GroupBuy::getPcSkuId, skuId));
        if (one != null) {
            BeanUtil.copyProperties(one, groupBuyResult);
        }
        return groupBuyResult;
    }

    /**
     * 修改活动状态
     *
     * @param id
     * @param state
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/22 14:40
     */
    @Override
    public Boolean updateState(Long id, Integer state) {
        GroupBuy groupBuy = new GroupBuy();
        groupBuy.setState(state);
        groupBuy.setId(id);
        return updateById(groupBuy);
    }

    /**
     * 保存团购活动
     *
     * @param groupBuy
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/22 14:49
     */
    @Override
	@Transactional(rollbackFor = Exception.class)
    public Boolean saveGroupBuy(GroupBuy groupBuy) {

        // 同一个商品只会存在一个可用的团购活动
        List<GroupBuy> list = list(Wrappers.<GroupBuy> query().lambda().eq(GroupBuy::getPcSkuId, groupBuy.getPcSkuId())
                .eq(GroupBuy::getState, 1));

        if (!CollectionUtils.isEmpty(list)) {
            return Boolean.FALSE;
        }
        // 保存团购活动
        save(groupBuy);
        // 发送开始定时任务至qc
        quartzLogService.sendQcQuartz(groupBuy.getId(), QuartzTypeEnum.GROUPBUY.getCode(), 1, groupBuy.getUseStartTime());
        // 发送结束定时任务至qc
        quartzLogService.sendQcQuartz(groupBuy.getId(), QuartzTypeEnum.GROUPBUY.getCode(), -1, groupBuy.getUseEndTime());
        return Boolean.TRUE;
    }

}
