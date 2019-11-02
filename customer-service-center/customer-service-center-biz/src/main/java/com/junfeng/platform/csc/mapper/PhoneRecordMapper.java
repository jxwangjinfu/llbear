package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.PhoneRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 话机接听记录表
 *
 * @author lw
 * @date 2019-09-29 10:38:04
 */
public interface PhoneRecordMapper extends BaseMapper<PhoneRecord> {
  /**
    * 话机接听记录表简单分页查询
    * @param phoneRecord 话机接听记录表
    * @return
    */
  IPage<PhoneRecord> getPhoneRecordPage(Page page, @Param("phoneRecord") PhoneRecord phoneRecord);


}
