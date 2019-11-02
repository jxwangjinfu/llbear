package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.OnlineRecord;

/**
 * 人工在线服务记录表
 *
 * @author lw
 * @date 2019-09-29 10:48:12
 */
public interface OnlineRecordService extends IService<OnlineRecord> {

  /**
   * 人工在线服务记录表简单分页查询
   * @param onlineRecord 人工在线服务记录表
   * @return
   */
  IPage<OnlineRecord> getOnlineRecordPage(Page<OnlineRecord> page, OnlineRecord onlineRecord);


}
