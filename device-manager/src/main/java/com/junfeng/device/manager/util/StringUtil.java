package com.junfeng.device.manager.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import com.junfeng.device.manager.entity.ColorRibbonVo;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/30 10:28
 * @projectName pig
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * 功能描述: 获取读卡器SN编码的前一部分，即设备机具号
	 *
	 * @param sn sn编码
	 * @return java.lang.String
	 * @author: hanyx
	 * @createTime: 2019/10/30 10:32
	 */
	public static String getSnPart1(String sn) {
		String part1 = "";
		if (sn == null) {
			return part1;
		}
		int index = sn.indexOf("_");
		if (index != -1) {
			part1 = sn.substring(0, index);
		}
		return part1;
	}

	/**
	 * 功能描述: 获取读卡器SN编码的后一部分，即PSAM
	 *
	 * @param sn sn编码
	 * @return java.lang.String
	 * @author: hanyx
	 * @createTime: 2019/10/30 10:32
	 */
	public static String getPSAM(String sn) {
		String psam = "";
		if (sn == null) {
			return psam;
		}
		int index = sn.indexOf("_");
		if (index != -1) {
			psam = sn.substring(index + 1);
		}
		return psam;
	}

	/**
	 * 功能描述: 通过设备其他属性字段（otherPropertyJson）获取色带相关信息
	 *
	 * @param otherPropertyJson 设备其他属性字段
	 * @return com.junfeng.device.manager.entity.ColorRibbonVo
	 * @author: hanyx
	 * @createTime: 2019/10/30 11:31
	 */
	public static ColorRibbonVo getColorRibbon(String otherPropertyJson) {
		if (Strings.isNullOrEmpty(otherPropertyJson)) {
			return null;
		}

		JSONObject jsonObject = JSONUtil.parseObj(otherPropertyJson);
		Object colorRibbonCodeObj = jsonObject.get("colorRibbonCode");
		if (colorRibbonCodeObj == null) {
			return null;
		}
		Object colorRibbonRemainObj = jsonObject.get("colorRibbonRemain");
		if (colorRibbonRemainObj == null) {
			return null;
		}

		ColorRibbonVo colorRibbonVo = new ColorRibbonVo();
		colorRibbonVo.setColorRibbonCode(colorRibbonCodeObj.toString());
		colorRibbonVo.setColorRibbonRemain(colorRibbonRemainObj.toString());

		return colorRibbonVo;
	}
}
