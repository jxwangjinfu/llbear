package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.PhoneRecord;

/**
 * 话机接听记录表
 *
 * @author lw
 * @date 2019-09-29 10:38:04
 */
public interface PhoneRecordService extends IService<PhoneRecord> {

  /**
   * 话机接听记录表简单分页查询
   * @param phoneRecord 话机接听记录表
   * @return
   */
  IPage<PhoneRecord> getPhoneRecordPage(Page<PhoneRecord> page, PhoneRecord phoneRecord);

}
