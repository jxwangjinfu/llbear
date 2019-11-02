package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.WorkOrderRecord;
import com.junfeng.platform.csc.service.WorkOrderRecordService;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工单处理记录表
 *
 * @author lw
 * @date 2019-10-08 17:28:11
 */
@Api(tags = {"工单处理"})
@RestController
@AllArgsConstructor
@RequestMapping("/workorderrecord")
public class WorkOrderRecordController {

	private final WorkOrderRecordService workOrderRecordService;

	/**
	 * 简单分页查询
	 *
	 * @param page            分页对象
	 * @param workOrderRecord 工单处理记录表
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 工单处理记录表")
	@GetMapping("/page")
	public R<IPage<WorkOrderRecord>> getWorkOrderRecordPage(Page<WorkOrderRecord> page, WorkOrderRecord workOrderRecord) {
		return R.ok(workOrderRecordService.getWorkOrderRecordPage(page, workOrderRecord));
	}
}
