package com.junfeng.platform.csc.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.junfeng.platform.csc.api.result.DocumentResult;
import com.junfeng.platform.csc.api.result.QuestionResult;
import com.junfeng.platform.csc.api.result.TrainResult;
import com.junfeng.platform.csc.api.vo.*;
import com.junfeng.platform.csc.constant.CscConstant;
import com.junfeng.platform.csc.service.AiCustomerService;
import com.junfeng.platform.csc.util.UrlOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.List;

/**
 * 智能客服Service实现
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 11:11
 * @projectName pig
 */
@Slf4j
@Service("aiCustomerService")
public class AiCustomerServiceImpl implements AiCustomerService {

	private static final String UTF8 = "UTF-8";
	private static final String DATA = "data";
	private static final String MSG = "msg";
	private static final String RET = "ret";
	private static final String SUCCESS_CODE = "0";
	private static final String NO_SOURCE = "要训练的问题不在知识库中";

	/**
	 * 功能描述: 问答知识导入
	 *
	 * @param qaVo 问答知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	@Override
	public boolean loadAskAndAnswer(QuestionAnswerVo qaVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.LOAD_ASK_AND_ANSWER + URLEncoder.encode(qaVo.getQuestion(), UTF8) +
			"&answer=" + URLEncoder.encode(qaVo.getAnswer(), UTF8));
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		// 返回出错
		if (!SUCCESS_CODE.equals(jsonObject.get(RET).toString())) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return true;
	}

	/**
	 * 功能描述: 文档知识导入
	 *
	 * @param dkVo 文档知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	@Override
	public boolean loadDocumentKnowledge(DocumentKnowledgeVo dkVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.LOAD_DOCUMENT_KNOWLEDGE + URLEncoder.encode(dkVo.getParentTitle(), UTF8) +
			"&title=" + URLEncoder.encode(dkVo.getTitle(), UTF8) + "&docment=" + URLEncoder.encode(dkVo.getDocument(), UTF8));
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		// 返回出错
		if (!SUCCESS_CODE.equals(jsonObject.get(RET).toString())) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return true;
	}

	/**
	 * 功能描述: 训练问答
	 *
	 * @param tVo 训练问答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@Override
	public boolean trainAskAndAnswer(TrainQuestionAnswerVo tVo) throws Exception {
		String title = (tVo.getTitle() != null) ? URLEncoder.encode(tVo.getTitle(), UTF8) : URLEncoder.encode("", UTF8);
		String result = UrlOperate.getHTML(CscConstant.Url.TRAIN_ASK_AND_ANSWER + title +
			"&num=" + URLEncoder.encode(String.valueOf(tVo.getNum()), UTF8));
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		// 返回出错
		if (!SUCCESS_CODE.equals(jsonObject.get(RET).toString()) || NO_SOURCE.equals(jsonObject.get(MSG).toString())) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return true;
	}

	/**
	 * 功能描述: 查看训练结果
	 *
	 * @param trainResultVo 查看训练结果接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.TrainResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@Override
	public List<TrainResult> getTrainResult(TrainResultVo trainResultVo) throws Exception {
		JSONArray jsonArray = getTrainResultArray(trainResultVo, false);
		return JSONUtil.toList(jsonArray, TrainResult.class);
	}

	/**
	 * 功能描述:基于原始问句查看训练结果
	 *
	 * @param trainResultVo 基于原始问句查看训练结果接口请求参数
	 * @return java.util.List<com.junfeng.platform.csc.api.result.TrainResult>
	 * @throws Exception 异常
	 * @author: hanyx
	 * @createTime: 2019/10/23 10:53
	 */
	@Override
	public List<TrainResult> getTrainSourceResult(TrainResultVo trainResultVo) throws Exception {
		JSONArray jsonArray = getTrainResultArray(trainResultVo, true);
		return JSONUtil.toList(jsonArray, TrainResult.class);
	}

	/**
	 * 功能描述: 获取训练结果的json数组
	 *
	 * @param trainResultVo 查看训练结果接口请求参数
	 * @param isBaseSource  是否给予原始语句
	 * @return cn.hutool.json.JSONArray
	 * @author: hanyx
	 * @createTime: 2019/10/23 11:05
	 */
	private JSONArray getTrainResultArray(TrainResultVo trainResultVo, boolean isBaseSource) throws Exception {
		String title = (trainResultVo.getTitle() != null) ? URLEncoder.encode(trainResultVo.getTitle(), UTF8) : URLEncoder.encode("", UTF8);
		String result;
		if (isBaseSource) {
			result = UrlOperate.getHTML(CscConstant.Url.GET_TRAIN_SOURCE_RESULT + title +
				"&pagenum=" + URLEncoder.encode(String.valueOf(trainResultVo.getPagenum()), UTF8));
		} else {
			result = UrlOperate.getHTML(CscConstant.Url.GET_TRAIN_RESULT + title +
				"&pagenum=" + URLEncoder.encode(String.valueOf(trainResultVo.getPagenum()), UTF8));
		}
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		Object data = jsonObject.get(DATA);
		if (data == null) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return JSONUtil.parseArray(data.toString());
	}

	/**
	 * 功能描述: 删除训练结果
	 *
	 * @param dtrVo 删除训练结果请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@Override
	public boolean deleteTrainResult(DeleteTrainResultVo dtrVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.DELETE_TRAIN_RESULT + URLEncoder.encode(dtrVo.getQuestion(), UTF8));
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		// 返回出错
		if (!SUCCESS_CODE.equals(jsonObject.get(RET).toString())) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return true;
	}

	/**
	 * 功能描述: 获取解答
	 *
	 * @param questionVo 获取解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@Override
	public QuestionResult getAnswer(QuestionVo questionVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.GET_ANSWER + URLEncoder.encode(questionVo.getQuestion(), UTF8));
		log.info(result);
		JSONObject jsonObject = JSONUtil.parseObj(result);
		Object data = jsonObject.get(DATA);
		if (data == null) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return JSONUtil.toBean(data.toString(), QuestionResult.class);
	}

	/**
	 * 功能描述: 获取相似解答
	 *
	 * @param questionVo 获取相似解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@Override
	public QuestionResult getSimilarAnswer(QuestionVo questionVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.GET_SIMILAR_ANSWER + URLEncoder.encode(questionVo.getQuestion(), UTF8));
		log.info(result);

		JSONObject jsonObject = JSONUtil.parseObj(result);
		Object data = jsonObject.get(DATA);
		if (data == null) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return JSONUtil.toBean(data.toString(), QuestionResult.class);
	}

	/**
	 * 功能描述: 获取知识引导
	 *
	 * @param documentVo 获取知识引导接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.DocumentResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@Override
	public DocumentResult getDocument(DocumentVo documentVo) throws Exception {
		String result = UrlOperate.getHTML(CscConstant.Url.GET_DOCUMENT + URLEncoder.encode(documentVo.getTitle(), UTF8));
		log.info(result);
		JSONObject jsonObject = JSONUtil.parseObj(result);
		Object data = jsonObject.get(DATA);
		if (data == null) {
			throw new Exception(jsonObject.get(MSG).toString());
		}

		return JSONUtil.toBean(data.toString(), DocumentResult.class);
	}
}
