package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.RedEnvelopeQuart;
import org.apache.ibatis.annotations.Param;

/**
 * 红包定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:10
 */
public interface RedEnvelopeQuartMapper extends BaseMapper<RedEnvelopeQuart> {
  /**
    * 红包定时任务触发表简单分页查询
    * @param redEnvelopeQuart 红包定时任务触发表
    * @return
    */
  IPage<RedEnvelopeQuart> getRedEnvelopeQuartPage(Page page, @Param("redEnvelopeQuart") RedEnvelopeQuart redEnvelopeQuart);


}
