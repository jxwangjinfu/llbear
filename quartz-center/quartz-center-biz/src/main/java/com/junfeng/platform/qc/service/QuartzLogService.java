/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.junfeng.platform.qc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.qc.entity.QuartzLog;

/**
 * quartz日志表
 *
 * @author wangjf
 * @date 2019-09-18 09:09:37
 */
public interface QuartzLogService extends IService<QuartzLog> {

  /**
   * quartz日志表简单分页查询
   * @param quartzLog quartz日志表
   * @return
   */
  IPage<QuartzLog> getQuartzLogPage(Page<QuartzLog> page, QuartzLog quartzLog);


}
