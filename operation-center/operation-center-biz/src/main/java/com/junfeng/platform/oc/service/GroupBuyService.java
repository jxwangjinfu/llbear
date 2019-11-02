package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.result.GroupBuyResult;
import com.junfeng.platform.oc.entity.GroupBuy;

/**
 * 团购活动表
 *
 * @author wangjf
 * @date 2019-10-22 14:05:08
 */
public interface GroupBuyService extends IService<GroupBuy> {

    /**
     * 团购活动表简单分页查询
     *
     * @param groupBuy
     *            团购活动表
     * @return
     */
    IPage<GroupBuy> getGroupBuyPage(Page<GroupBuy> page, GroupBuy groupBuy);

    /**
     * 通过SKU_ID获取拼团设置
     *
     * @author: wangjf
     * @createTime: 2019/10/22 14:19
     * @param skuId
     * @return com.junfeng.platform.oc.entity.GroupBuy
     */
    GroupBuyResult getBySkuId(Long skuId);

    /**
     * 修改活动状态
     *
     * @author: wangjf
     * @createTime: 2019/10/22 14:40
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id, Integer state);

    /**
     * 保存团购活动
     *
     * @author: wangjf
     * @createTime: 2019/10/22 14:49
     * @param groupBuy
     * @return java.lang.Boolean
     */
    Boolean saveGroupBuy(GroupBuy groupBuy);

}
