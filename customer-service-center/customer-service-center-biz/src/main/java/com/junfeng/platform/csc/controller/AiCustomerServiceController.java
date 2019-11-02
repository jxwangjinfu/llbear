package com.junfeng.platform.csc.controller;

import com.junfeng.platform.csc.api.result.DocumentResult;
import com.junfeng.platform.csc.api.result.QuestionResult;
import com.junfeng.platform.csc.api.result.TrainResult;
import com.junfeng.platform.csc.api.vo.*;
import com.junfeng.platform.csc.service.AiCustomerService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 智能客服Controller
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 10:15
 * @projectName pig
 */
@Api(tags = {"智能客服"})
@RestController
@AllArgsConstructor
@RequestMapping("/aiservice")
public class AiCustomerServiceController {

	private final AiCustomerService aiCustomerService;

	/**
	 * 功能描述: 问答知识导入
	 *
	 * @param qaVo 问答知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	@ApiOperation(value = "问答知识导入", notes = "参数： qaVo")
	@SysLog("问答知识导入")
	@PostMapping("/loadAskAndAnswer")
	public R<Boolean> loadAskAndAnswer(@RequestBody @Validated QuestionAnswerVo qaVo) {
		try {
			return R.ok(aiCustomerService.loadAskAndAnswer(qaVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());

		}
	}

	/**
	 * 功能描述: 文档知识导入
	 *
	 * @param dkVo 文档知识导入接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:39
	 */
	@ApiOperation(value = "文档知识导入", notes = "参数： dkVo")
	@SysLog("文档知识导入")
	@PostMapping("/loadDocumentKnowledge")
	public R<Boolean> loadDocumentKnowledge(@RequestBody @Validated DocumentKnowledgeVo dkVo) {
		try {
			return R.ok(aiCustomerService.loadDocumentKnowledge(dkVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 训练问答
	 *
	 * @param tVo 训练问答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@ApiOperation(value = "训练问答", notes = "参数： tVo")
	@SysLog("训练问答")
	@PostMapping("/trainAskAndAnswer")
	public R<Boolean> trainAskAndAnswer(@RequestBody @Validated TrainQuestionAnswerVo tVo) {
		try {
			return R.ok(aiCustomerService.trainAskAndAnswer(tVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 查看训练结果
	 *
	 * @param trainResultVo 查看训练结果接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.TrainResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@ApiOperation(value = "查看训练结果", notes = "参数： trainResultVo")
	@SysLog("查看训练结果")
	@GetMapping("/getTrainResult")
	public R<List<TrainResult>> getTrainResult(@Validated TrainResultVo trainResultVo) {
		try {
			return R.ok(aiCustomerService.getTrainResult(trainResultVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 基于原始问句查看训练结果
	 *
	 * @param trainResultVo 基于原始问句查看训练结果接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.util.List < com.junfeng.platform.csc.api.result.TrainResult>>
	 * @author: hanyx
	 * @createTime: 2019/10/23 11:07
	 */
	@ApiOperation(value = "基于原始问句查看训练结果", notes = "参数： trainResultVo")
	@SysLog("基于原始问句查看训练结果")
	@GetMapping("/getTrainSourceResult")
	public R<List<TrainResult>> getTrainSourceResult(@Validated TrainResultVo trainResultVo) {
		try {
			return R.ok(aiCustomerService.getTrainSourceResult(trainResultVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 删除训练结果
	 *
	 * @param dtrVo 删除训练结果请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:40
	 */
	@ApiOperation(value = "删除训练结果", notes = "参数： dtrVo")
	@SysLog("删除训练结果")
	@DeleteMapping("/deleteTrainResult")
	public R<Boolean> deleteTrainResult(@Validated DeleteTrainResultVo dtrVo) {
		try {
			return R.ok(aiCustomerService.deleteTrainResult(dtrVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 获取解答
	 *
	 * @param questionVo 获取解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@ApiOperation(value = "获取解答", notes = "参数： questionVo")
	@SysLog("获取解答")
	@GetMapping("/getAnswer")
	public R<QuestionResult> getAnswer(@Validated QuestionVo questionVo) {
		try {
			return R.ok(aiCustomerService.getAnswer(questionVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}

	/**
	 * 功能描述: 获取相似解答
	 *
	 * @param questionVo 获取相似解答接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.QuestionResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@ApiOperation(value = "获取相似解答", notes = "参数： questionVo")
	@SysLog("获取相似解答")
	@GetMapping("/getSimilarAnswer")
	public R<QuestionResult> getSimilarAnswer(@Validated QuestionVo questionVo) {
		try {
			return R.ok(aiCustomerService.getSimilarAnswer(questionVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}


	/**
	 * 功能描述: 获取知识引导
	 *
	 * @param documentVo 获取知识引导接口请求参数
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.csc.api.result.DocumentResult>
	 * @author: hanyx
	 * @createTime: 2019/10/21 9:41
	 */
	@ApiOperation(value = "获取知识引导", notes = "参数： documentVo")
	@SysLog("获取知识引导")
	@GetMapping("/getDocument")
	public R<DocumentResult> getDocument(@Validated DocumentVo documentVo) {
		try {
			return R.ok(aiCustomerService.getDocument(documentVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(e.getMessage());
		}
	}
}
