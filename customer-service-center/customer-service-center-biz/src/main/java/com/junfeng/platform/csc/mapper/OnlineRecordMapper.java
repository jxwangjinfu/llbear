package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.OnlineRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 人工在线服务记录表
 *
 * @author lw
 * @date 2019-09-29 10:48:12
 */
public interface OnlineRecordMapper extends BaseMapper<OnlineRecord> {
  /**
    * 人工在线服务记录表简单分页查询
    * @param onlineRecord 人工在线服务记录表
    * @return
    */
  IPage<OnlineRecord> getOnlineRecordPage(Page page, @Param("onlineRecord") OnlineRecord onlineRecord);


}
