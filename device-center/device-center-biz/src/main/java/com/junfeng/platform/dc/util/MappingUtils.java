package com.junfeng.platform.dc.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: 映射工具类
 */
public final class MappingUtils {

	private MappingUtils() {

	}

	public static <T> List<T> mappingList(List<?> list, Class<T> clazz) {
		if (CheckUtils.isEmpty(list)) {
			return null;
		}

		List<T> resultList = new ArrayList<T>();
		for (Object object : list) {
			resultList.add(mapping(object, clazz));
		}
		return resultList;
	}

	public static <T> T mapping(Object object, Class<T> clazz) {
		if (object == null) {
			return null;
		}

		T result = ReflectUtils.newInstance(clazz);
		return mappingObject(object, result);
	}

	public static <T> T mappingObject(Object object, T target) {
		if (object == null) {
			return null;
		}

		Field[] fields = target.getClass().getDeclaredFields();
		if (CheckUtils.isEmpty(fields)) {
			return target;
		}

		for (Field field : fields) {
			try {
				field.setAccessible(true);
				//static,final 不进行插入操作
				if (ReflectUtils.isFinal(field) || ReflectUtils.isStatic(field)) {
					continue;
				}

				//列为空不操作
				String value = ReflectUtils.getFieldString(object, field.getName());
				if (CheckUtils.isEmpty(value)) {
					continue;
				}

				if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
					field.set(target, ParseUtils.parseBoolean(value));
				} else if (field.getType() == Byte.TYPE || field.getType() == Byte.class) {
					field.set(target, ParseUtils.parseByte(value));
				} else if (field.getType() == Short.TYPE || field.getType() == Short.class) {
					field.set(target, ParseUtils.parseShort(value));
				} else if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
					field.set(target, ParseUtils.parseInt(value));
				} else if (field.getType() == Long.TYPE || field.getType() == Long.class) {
					field.set(target, ParseUtils.parseLong(value));
				} else if (field.getType() == Float.TYPE || field.getType() == Float.class) {
					field.set(target, ParseUtils.parseFloat(value));
				} else if (field.getType() == Double.TYPE || field.getType() == Double.class) {
					field.set(target, ParseUtils.parseDouble(value));
				} else if (field.getType() == String.class) {
					field.set(target, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return target;
	}
}
