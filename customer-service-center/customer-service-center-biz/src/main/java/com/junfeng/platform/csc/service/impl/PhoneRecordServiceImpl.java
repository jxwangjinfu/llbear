package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.PhoneRecord;
import com.junfeng.platform.csc.service.PhoneRecordService;
import com.junfeng.platform.csc.mapper.PhoneRecordMapper;
import org.springframework.stereotype.Service;

/**
 * 话机接听记录表
 *
 * @author lw
 * @date 2019-09-29 10:38:04
 */
@Service("phoneRecordService")
public class PhoneRecordServiceImpl extends ServiceImpl<PhoneRecordMapper, PhoneRecord> implements PhoneRecordService {

  /**
   * 话机接听记录表简单分页查询
   * @param phoneRecord 话机接听记录表
   * @return
   */
  @Override
  public IPage<PhoneRecord> getPhoneRecordPage(Page<PhoneRecord> page, PhoneRecord phoneRecord){
      return baseMapper.getPhoneRecordPage(page,phoneRecord);
  }

}
