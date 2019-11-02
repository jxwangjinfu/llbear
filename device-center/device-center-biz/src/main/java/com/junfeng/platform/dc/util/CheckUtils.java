package com.junfeng.platform.dc.util;

import com.junfeng.platform.dc.constant.DeviceCenterConstant;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/12 15:39
 * @projectName pig
 */
public final class CheckUtils {

	private CheckUtils() {
	}

	public static boolean isEmpty(Object object) {
		return object == null || isEmpty(object.toString());
	}

	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0 || "null".equals(str.toLowerCase()));
	}


	public static boolean isEmpty(Collection collection) {
		return ((collection == null) || (collection.isEmpty()));
	}

	public static boolean isEmpty(Map map) {
		return ((map == null) || (map.isEmpty()));
	}


	public static <V> boolean isEmpty(V[] sourceArray) {
		return (sourceArray == null || sourceArray.length == 0);
	}

	public static boolean isOrEquals(String leftData, String... rightDataArray) {
		if (isEmpty(rightDataArray)) {
			return false;
		}

		for (String item : rightDataArray) {
			if (isEquals(leftData, item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEquals(String data1, String data2) {
		if (isAndEmpty(data1, data2)) {
			return false;
		} else if (isOrEmpty(data1, data2)) {
			return false;
		} else {
			return data1.equals(data2);
		}
	}

	public static boolean isAndEmpty(String... args) {
		if (isEmpty(args)) {
			return true;
		}

		int emptyCount = 0;
		for (String str : args) {
			if (isEmpty(str)) {
				emptyCount++;
			}
		}

		return args.length == emptyCount;
	}

	public static boolean isOrEmpty(String... args) {
		if (isEmpty(args)) {
			return true;
		}

		for (String str : args) {
			if (isEmpty(str)) {
				return true;
			}
		}

		return false;
	}


	/**
	 * 功能描述:
	 *
	 * @param delFlag 待传入String
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/12 16:09
	 */
	public static boolean isLogicDelete(String delFlag) {
		if (isEmpty(delFlag)) {
			return false;
		}
		return DeviceCenterConstant.DEL_FLAG_TRUE.equals(delFlag);
	}

	/**
	 * 功能描述: 判断字符串是否为手机号
	 *
	 * @param number 字符串
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/16 14:59
	 */
	public static boolean isPhoneNumber(String number) {
		return !CheckUtils.isEmpty(number) && (number.length() == 11) && number.matches("[1][3-9]\\d{9}");
	}

	/**
	 * 功能描述: 判断字符串是否为数字
	 *
	 * @param number 字符串
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/16 15:42
	 */
	public static boolean isNumber(String number) {
		if (isEmpty(number)) {
			return false;
		}
		return number.matches("\\d+");
	}


	/**
	 * 功能描述: 判断字符串是否为手机号
	 *
	 * @param str 字符串
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/16 15:29
	 */
	public static boolean isIP(String str) {
		if (CheckUtils.isEmpty(str)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]" +
			"|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述: 判断字符串是否为字母数字组成的编码
	 *
	 * @param str 字符串
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/16 15:33
	 */
	public static boolean isCode(String str) {
		if (isEmpty(str)) {
			return false;
		}
		return str.matches("[A-Za-z0-9]+");
	}

}
