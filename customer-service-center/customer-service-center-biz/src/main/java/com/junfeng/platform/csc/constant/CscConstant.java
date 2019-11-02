package com.junfeng.platform.csc.constant;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 14:44
 * @projectName pig
 */
public class CscConstant {

	public static class Url {

		/**
		 * 问答知识导入接口地址
		 */
		public static final String LOAD_ASK_AND_ANSWER = "http://192.168.199.34:8083/loadaskandanswer?question=";

		/**
		 * 文档知识导入接口地址
		 */
		public static final String LOAD_DOCUMENT_KNOWLEDGE = "http://192.168.199.34:8083/loaddocument?parentTitle=";

		/**
		 * 训练问答接口地址
		 */
		public static final String TRAIN_ASK_AND_ANSWER = "http://192.168.199.34:8083/trainaskandanswer?title=";

		/**
		 * 查看训练结果接口地址
		 */
		public static final String GET_TRAIN_RESULT = "http://192.168.199.34:8083/searchtrain?title=";

		/**
		 * 基于原始问句查看训练结果接口地址
		 */
		public static final String GET_TRAIN_SOURCE_RESULT = "http://192.168.199.34:8083/searchtrainsource?title=";

		/**
		 * 删除训练结果接口地址
		 */
		public static final String DELETE_TRAIN_RESULT = "http://192.168.199.34:8083/deletetrain?question=";

		/**
		 * 获取解答接口地址
		 */
		public static final String GET_ANSWER = "http://192.168.199.34:8083/getanswer?question=";

		/**
		 * 获取相似解答接口地址
		 */
		public static final String GET_SIMILAR_ANSWER = "http://192.168.199.34:8083/getsimilaranswer?question=";

		/**
		 * 获取知识引导接口地址
		 */
		public static final String GET_DOCUMENT = "http://192.168.199.34:8083/getdocument?title=";
	}
}
