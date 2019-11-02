package com;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.junfeng.platform.oc.api.vo.BuyGiftLevelGoodsVO;
import com.junfeng.platform.oc.api.vo.BuyGiftLevelVO;
import com.junfeng.platform.oc.api.vo.BuyGiftVO;
import com.junfeng.platform.oc.api.vo.OrderVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/9/30 14:23
 * @projectName pig
 */
public class TestMain {

	public static void main(String[] args) {

		BuyGiftLevelGoodsVO buyGiftLevelGoodsVO1 = new BuyGiftLevelGoodsVO();
		buyGiftLevelGoodsVO1.setGiftNumber(1);
		buyGiftLevelGoodsVO1.setType(1);
		buyGiftLevelGoodsVO1.setTypeId(41L);

		BuyGiftLevelGoodsVO buyGiftLevelGoodsVO2 = new BuyGiftLevelGoodsVO();
		buyGiftLevelGoodsVO2.setGiftNumber(1);
		buyGiftLevelGoodsVO2.setType(2);
		buyGiftLevelGoodsVO2.setTypeId(10L);

		BuyGiftLevelGoodsVO buyGiftLevelGoodsVO3 = new BuyGiftLevelGoodsVO();
		buyGiftLevelGoodsVO3.setGiftNumber(1);
		buyGiftLevelGoodsVO3.setType(3);
		buyGiftLevelGoodsVO3.setTypeId(2L);
		List<BuyGiftLevelGoodsVO> buyGiftLevelGoodsVOList = Lists.newArrayList(buyGiftLevelGoodsVO1,
			buyGiftLevelGoodsVO2, buyGiftLevelGoodsVO3);

		BuyGiftLevelVO buyGiftLevelVO = new BuyGiftLevelVO();
		buyGiftLevelVO.setGoodsList(buyGiftLevelGoodsVOList);
		buyGiftLevelVO.setOrderUsePre(10);
		buyGiftLevelVO.setPoints(10);

		List<BuyGiftLevelVO> buyGiftLevelVOList = Lists.newArrayList(buyGiftLevelVO);

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		BuyGiftVO buyGiftVO = new BuyGiftVO();

		buyGiftVO.setGiftName("java测试");
		buyGiftVO.setLevelList(buyGiftLevelVOList);
		buyGiftVO.setLevelType(1);
		buyGiftVO.setUseEndTime(LocalDateTime.parse("2019-10-25 15:10:32",df));
		buyGiftVO.setUseStartTime(LocalDateTime.parse("2019-10-25 15:50:32",df));

		System.out.println(JSONUtil.toJsonStr(buyGiftVO));


		OrderVO orderVO = new OrderVO();
		orderVO.setTotalPrice(130L);
		orderVO.setMemberId(1L);
		orderVO.setOrderNo("123456789");
		System.out.println(JSONUtil.toJsonStr(orderVO));

	}
}
