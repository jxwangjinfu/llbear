package com.junfeng.platform.qc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.qc.entity.QuartzEntity;
import com.junfeng.platform.qc.entity.TypeBlob;

/**
 * 定时任务对应的Mapper
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 下午4:22:31
 * @version 1.0
 */
public interface QuartzMapper extends BaseMapper<QuartzEntity> {

    /**
     * 查询任务列表
     * 
     * @param page
     * @param quartzDTO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 下午2:48:22
     */
    IPage<QuartzEntity> getQuartzPage(Page page, @Param("query") QuartzEntity quartzDTO);
    
    /**
     * 查询单个任务
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 下午4:56:11
     */
    QuartzEntity getQuartz(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    /**
     * 通过jobName和jobGroup获取任务参数的字节数组
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 下午4:50:37
     */
    TypeBlob getJobData(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

}
