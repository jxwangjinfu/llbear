package com.junfeng.platform.dc.util;

/**
 * 功能描述: 解析工具类
 */
public final class ParseUtils {

	private ParseUtils() {

	}

	public static int parseInt(String s) {
		int result = 0;
		try {
			result = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return result;
	}

	public static double parseDouble(String s) {
		double result = 0d;
		try {
			result = Double.parseDouble(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static float parseFloat(String s) {
		float result = 0f;
		try {
			result = Float.parseFloat(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static long parseLong(String s) {
		long result = 0L;
		try {
			result = Long.parseLong(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public static boolean parseBoolean(String s) {
		try {
			if (CheckUtils.isEmpty(s)) {
				return false;
			} else if (CheckUtils.isOrEquals(s.toLowerCase(), "true", "false")) {
				return Boolean.parseBoolean(s);
			} else {
				return parseInt(s) == 1;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public static byte parseByte(String s) {
		return (byte) parseInt(s);
	}

	public static short parseShort(String s) {
		short result = 0;
		try {
			result = Short.parseShort(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
