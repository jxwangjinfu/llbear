package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.PhoneRecord;
import com.junfeng.platform.csc.service.PhoneRecordService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话机接听记录表
 *
 * @author lw
 * @date 2019-09-29 10:38:04
 */
/*@Api(tags = {"话机接听记录表"},hidden = true)*/
@RestController
@AllArgsConstructor
@RequestMapping("/phonerecord")
public class PhoneRecordController {

  private final  PhoneRecordService phoneRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param phoneRecord 话机接听记录表
   * @return
   */
/*  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 话机接听记录表")*/
  @GetMapping("/page")
  public R<IPage<PhoneRecord>> getPhoneRecordPage(Page<PhoneRecord> page, PhoneRecord phoneRecord) {
    return R.ok(phoneRecordService.getPhoneRecordPage(page,phoneRecord));
  }
}
