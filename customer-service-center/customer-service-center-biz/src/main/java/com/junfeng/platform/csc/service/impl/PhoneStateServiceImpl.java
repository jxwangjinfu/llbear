package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.PhoneState;
import com.junfeng.platform.csc.service.PhoneStateService;
import com.junfeng.platform.csc.mapper.PhoneStateMapper;
import org.springframework.stereotype.Service;

/**
 * 话机当前状态表
 *
 * @author lw
 * @date 2019-09-29 10:38:14
 */
@Service("phoneStateService")
public class PhoneStateServiceImpl extends ServiceImpl<PhoneStateMapper, PhoneState> implements PhoneStateService {

  /**
   * 话机当前状态表简单分页查询
   * @param phoneState 话机当前状态表
   * @return
   */
  @Override
  public IPage<PhoneState> getPhoneStatePage(Page<PhoneState> page, PhoneState phoneState){
      return baseMapper.getPhoneStatePage(page,phoneState);
  }

}
