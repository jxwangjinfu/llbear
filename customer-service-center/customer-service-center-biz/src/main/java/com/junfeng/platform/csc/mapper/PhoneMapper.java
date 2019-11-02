package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.Phone;
import org.apache.ibatis.annotations.Param;

/**
 * 话机信息表
 *
 * @author lw
 * @date 2019-09-29 10:37:52
 */
public interface PhoneMapper extends BaseMapper<Phone> {
  /**
    * 话机信息表简单分页查询
    * @param phone 话机信息表
    * @return
    */
  IPage<Phone> getPhonePage(Page page, @Param("phone") Phone phone);


}
