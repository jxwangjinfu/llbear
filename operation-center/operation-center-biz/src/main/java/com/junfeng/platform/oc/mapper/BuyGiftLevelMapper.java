package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.BuyGiftLevel;
import org.apache.ibatis.annotations.Param;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-15 14:32:05
 */
public interface BuyGiftLevelMapper extends BaseMapper<BuyGiftLevel> {
  /**
    * 买赠等级表简单分页查询
    * @param buyGiftLevel 买赠等级表
    * @return
    */
  IPage<BuyGiftLevel> getBuyGiftLevelPage(Page page, @Param("buyGiftLevel") BuyGiftLevel buyGiftLevel);
/**
 * 获取买赠
 * @author: wangjf
 * @createTime: 2019/10/18 14:44
 * @param orderVO
 * @return com.junfeng.platform.oc.entity.BuyGiftLevel
 */
	BuyGiftLevel getBuyGiftLevel(@Param("orderVO") OrderVO orderVO);


}
