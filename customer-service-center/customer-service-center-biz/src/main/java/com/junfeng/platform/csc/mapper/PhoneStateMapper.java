package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.PhoneState;
import org.apache.ibatis.annotations.Param;

/**
 * 话机当前状态表
 *
 * @author lw
 * @date 2019-09-29 10:38:14
 */
public interface PhoneStateMapper extends BaseMapper<PhoneState> {
  /**
    * 话机当前状态表简单分页查询
    * @param phoneState 话机当前状态表
    * @return
    */
  IPage<PhoneState> getPhoneStatePage(Page page, @Param("phoneState") PhoneState phoneState);


}
