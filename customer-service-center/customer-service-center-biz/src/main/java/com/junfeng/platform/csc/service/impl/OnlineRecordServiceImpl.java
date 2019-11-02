package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.OnlineRecord;
import com.junfeng.platform.csc.service.OnlineRecordService;
import com.junfeng.platform.csc.mapper.OnlineRecordMapper;
import org.springframework.stereotype.Service;

/**
 * 人工在线服务记录表
 *
 * @author lw
 * @date 2019-09-29 10:48:12
 */
@Service("onlineRecordService")
public class OnlineRecordServiceImpl extends ServiceImpl<OnlineRecordMapper, OnlineRecord> implements OnlineRecordService {

  /**
   * 人工在线服务记录表简单分页查询
   * @param onlineRecord 人工在线服务记录表
   * @return
   */
  @Override
  public IPage<OnlineRecord> getOnlineRecordPage(Page<OnlineRecord> page, OnlineRecord onlineRecord){
      return baseMapper.getOnlineRecordPage(page,onlineRecord);
  }

}
