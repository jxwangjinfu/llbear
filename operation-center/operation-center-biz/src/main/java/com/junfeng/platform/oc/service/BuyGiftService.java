package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.vo.BuyGiftVO;
import com.junfeng.platform.oc.entity.BuyGift;

/**
 * 买赠表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:35
 */
public interface BuyGiftService extends IService<BuyGift> {

    /**
     * 买赠表简单分页查询
     *
     * @param buyGift
     *            买赠表
     * @return
     */
    IPage<BuyGift> getBuyGiftPage(Page<BuyGift> page, BuyGift buyGift);

    /**
     * 修改状态
     *
     * @author: wangjf
     * @createTime: 2019/10/15 16:11
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id, Integer state);

    /**
     * 保存买赠
     * @author: wangjf
     * @createTime: 2019/10/15 16:58
     * @param buyGift
     * @return java.lang.Boolean
     */
    Boolean saveBuyGift(BuyGift buyGift);

    /**
     * 保存买赠
     * @author: wangjf
     * @createTime: 2019/10/25 14:33
     * @param buyGiftVO
     * @return java.lang.Boolean
     */
    Boolean saveBuyGiftVO(BuyGiftVO buyGiftVO);

}
