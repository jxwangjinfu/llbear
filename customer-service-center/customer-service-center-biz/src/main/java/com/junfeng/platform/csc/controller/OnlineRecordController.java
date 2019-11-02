package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.OnlineRecord;
import com.junfeng.platform.csc.service.OnlineRecordService;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人工在线服务记录表
 *
 * @author lw
 * @date 2019-09-29 10:48:12
 */
@Api(tags = {"人工在线服务记录表"})
@RestController
@AllArgsConstructor
@RequestMapping("/onlinerecord")
public class OnlineRecordController {

  private final  OnlineRecordService onlineRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param onlineRecord 人工在线服务记录表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 人工在线服务记录表")
  @GetMapping("/page")
  public R<IPage<OnlineRecord>> getOnlineRecordPage(Page<OnlineRecord> page, OnlineRecord onlineRecord) {
    return R.ok(onlineRecordService.getOnlineRecordPage(page,onlineRecord));
  }
}
