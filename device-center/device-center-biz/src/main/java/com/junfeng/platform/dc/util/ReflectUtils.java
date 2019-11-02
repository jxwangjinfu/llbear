package com.junfeng.platform.dc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 功能描述: 反射工具类
 */
public final class ReflectUtils {

	private ReflectUtils() {

	}

	public static Class forClass(String className) {
		try {
			return Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String getFieldString(Object obj, String propertyName) {
		try {
			Field field = obj.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			Object result = field.get(obj);
			return result == null ? null : result.toString();
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		return null;
	}

	public static boolean isFinal(Field field) {
		if (field == null) {
			return false;
		}
		return Modifier.isFinal(field.getModifiers());
	}

	public static boolean isStatic(Field field) {
		if (field == null) {
			return false;
		}
		return Modifier.isStatic(field.getModifiers());
	}

}

