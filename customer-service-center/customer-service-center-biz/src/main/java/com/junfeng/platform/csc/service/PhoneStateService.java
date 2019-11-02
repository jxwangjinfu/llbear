package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.PhoneState;

/**
 * 话机当前状态表
 *
 * @author lw
 * @date 2019-09-29 10:38:14
 */
public interface PhoneStateService extends IService<PhoneState> {

  /**
   * 话机当前状态表简单分页查询
   * @param phoneState 话机当前状态表
   * @return
   */
  IPage<PhoneState> getPhoneStatePage(Page<PhoneState> page, PhoneState phoneState);


}
