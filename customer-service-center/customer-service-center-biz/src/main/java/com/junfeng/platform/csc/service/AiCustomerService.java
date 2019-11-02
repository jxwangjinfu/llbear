package com.junfeng.platform.csc.service;

import com.junfeng.platform.csc.api.result.DocumentResult;
import com.junfeng.platform.csc.api.result.QuestionResult;
import com.junfeng.platform.csc.api.result.TrainResult;
import com.junfeng.platform.csc.api.vo.*;

import java.util.List;

/**
 * 智能客服Service
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 11:07
 * @projectName pig
 */
public interface AiCustomerService {

	/**
	 * 功能描述: 问答知识导入
	 *
	 * @param qaVo 问答知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	boolean loadAskAndAnswer(QuestionAnswerVo qaVo) throws Exception;

	/**
	 * 功能描述: 文档知识导入
	 *
	 * @param dkVo 文档知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	boolean loadDocumentKnowledge(DocumentKnowledgeVo dkVo) throws Exception;

	/**
	 * 功能描述: 训练问答
	 *
	 * @param tVo 训练问答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	boolean trainAskAndAnswer(TrainQuestionAnswerVo tVo) throws Exception;

	/**
	 * 功能描述: 查看训练结果
	 *
	 * @param trainResultVo 查看训练结果接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.TrainResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	List<TrainResult> getTrainResult(TrainResultVo trainResultVo) throws Exception;

	/**
	 * 功能描述:基于原始问句查看训练结果
	 *
	 * @param trainResultVo 基于原始问句查看训练结果接口请求参数
	 * @return java.util.List<com.junfeng.platform.csc.api.result.TrainResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/23 10:53
	 */
	List<TrainResult> getTrainSourceResult(TrainResultVo trainResultVo) throws Exception;

	/**
	 * 功能描述: 删除训练结果
	 *
	 * @param dtrVo 删除训练结果请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	boolean deleteTrainResult(DeleteTrainResultVo dtrVo) throws Exception;

	/**
	 * 功能描述: 获取解答
	 *
	 * @param questionVo 获取解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	QuestionResult getAnswer(QuestionVo questionVo) throws Exception;

	/**
	 * 功能描述: 获取相似解答
	 *
	 * @param questionVo 获取相似解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	QuestionResult getSimilarAnswer(QuestionVo questionVo) throws Exception;

	/**
	 * 功能描述: 获取知识引导
	 *
	 * @param documentVo 获取知识引导接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.DocumentResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	DocumentResult getDocument(DocumentVo documentVo) throws Exception;

}
