/**
 * Copyright (c) 2005-2012 springside.org.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.junfeng.platform.payment.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 *
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 *
 * 封装不同的输出风格, 使用不同的Include.
 *
 * ObjectMapper在映射对象时可以是单例的 但其他功能需要测试 例如转为treeNode
 *
 *
 */
@SuppressWarnings("unchecked")
public class AsJsonMapper {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
	}

	private AsJsonMapper() {

	}

	/**
	 * 判断object是否为null。为null则抛出异常
	 *
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object) {
		if (object == null || object.equals("")) {
			throw new IllegalArgumentException("object is null");
		}
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 */
	public static String toJson(Object object) throws JsonProcessingException {
		notNull(object);
		return mapper.writeValueAsString(object);
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 *
	 * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
	 *
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
	 *
	 * @see #fromJson(String, JavaType)
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) throws IOException {
		notNull(jsonString);
		return mapper.readValue(jsonString, clazz);

	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
	 *
	 * @see #createCollectionType(Class, Class...)
	 */
	public static <T> T fromJson(String jsonString, JavaType javaType) throws IOException {
		notNull(jsonString);
		return (T) mapper.readValue(jsonString, javaType);
	}

	/**
	 * 構造泛型的Collection Type如: ArrayList<MyBean>,
	 * 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 */
	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 修改json结构 向指定位置添加字段
	 *
	 * @param json
	 *            原始json 字符串格式
	 * @param paths
	 *            要添加的节点位置 ，使用.分割。例如 nodeName1.childNodeName 将新节点添加到 childNode下
	 * @param nodeName
	 *            新节点名称
	 * @param pojo
	 *            新节点内容
	 * @return 新json
	 * @throws RuntimeException
	 * @throws IOException
	 */
	public static String addNodeToPath(String json, String paths, String nodeName, Object pojo) throws RuntimeException, IOException {
		ObjectMapper mapper = new ObjectMapper();// 为了线程安全
		JsonNode rootNode;

		rootNode = mapper.readValue(json, JsonNode.class);

		if (!(rootNode instanceof ObjectNode)) {
			throw new RuntimeException("AsJsonMapper.addJSONFields fail,无法识别JSON根对象!");
		}
		if (StringUtils.isEmpty(paths)) {
			((ObjectNode) rootNode).putPOJO(nodeName, pojo);
		} else {
			JsonNode parentnode = rootNode;
			if (paths.indexOf(".") == -1) {
				parentnode = rootNode.path(paths);
			} else {
				String[] pathArr = paths.split("\\.");
				for (String path : pathArr) {
					if (parentnode != null) {
						parentnode = parentnode.path(path);
					} else {
						throw new RuntimeException("AsJsonMapper.addJSONFields fail,试图在不正确的位置添加节点!");
					}
				}
			}
			if (parentnode instanceof ObjectNode) {
				((ObjectNode) parentnode).putPOJO(nodeName, pojo);
			} else {
				throw new RuntimeException("AsJsonMapper.addJSONFields fail,试图在不正确的位置添加节点!");
			}
		}

		return mapper.writeValueAsString(rootNode);

	}

	/**
	 * 修改json结构 向根节点添加字段
	 *
	 * @param json
	 *            原始json
	 * @param nodeName
	 *            新节点名称
	 * @param pojo
	 *            新节点内容
	 * @return 新json
	 * @throws RuntimeException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static String addNode(String json, String nodeName, Object pojo) throws RuntimeException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();// 为了线程安全
		JsonNode rootNode;

		rootNode = mapper.readValue(json, JsonNode.class);

		if (!(rootNode instanceof ObjectNode)) {
			throw new RuntimeException("AsJsonMapper.addJSONFields fail,无法识别JSON根对象!");
		}
		((ObjectNode) rootNode).putPOJO(nodeName, pojo);

		return mapper.writeValueAsString(rootNode);

	}

	/**
	 * 修改json结构 删除字段
	 *
	 * @param json
	 * @param fields
	 *            field可以是.隔开 代表层级 remove最后一级
	 * @return 新json
	 * @throws RuntimeException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static String removeNodes(String json, List<String> fields) throws RuntimeException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();// 为了线程安全
		JsonNode rootNode;

		rootNode = mapper.readValue(json, JsonNode.class);

		if (rootNode instanceof ObjectNode) {
			ObjectNode on = (ObjectNode) rootNode;
			for (String f : fields) {
				if (f.indexOf(".") == -1) { // 如果没有.直接从根节点remove此field
					JsonNode removeNode = on.remove(f);
					if (removeNode == null) {
						throw new RuntimeException("AsMapper.removeNodes方法试图移除不正确的节点:" + f);
					}
				} else {
					String[] arr = f.split("\\.");
					JsonNode node = on;
					for (int i = 0; i < arr.length; i++) {
						// field路径不正确时 忽略此field
						if (node != null && node instanceof ObjectNode) {
							String path = arr[i];
							if (i == arr.length - 1) { // 移除最后一级节点
								JsonNode removeNode = ((ObjectNode) node).remove(path);
								if (removeNode == null) {
									throw new RuntimeException("AsMapper.removeNodes方法试图移除不正确的节点:" + path);
								}
							} else {
								node = node.path(path);// 向下遍历取得子节点
							}
						} else {
							throw new RuntimeException("AsMapper.removeNodes方法无法识别节点路径：" + f);
						}
					}
				}
			}
		} else {
			throw new RuntimeException("AsMapper.removeNodes方法无法识别JSON根对象!");
		}

		return mapper.writeValueAsString(rootNode);

	}

	public static String getJsonByName(String name, String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper m = new ObjectMapper();
		JsonNode rootNode = m.readValue(json, JsonNode.class);
		JsonNode node = rootNode.get(name);
		return m.writeValueAsString(node);
	}
}
