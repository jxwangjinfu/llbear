package com.junfeng.platform.qc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.entity.OutQuart;

/**
 * 外部定时任务
 *
 * @author wangjf
 * @date 2019-09-25 10:49:16
 */
public interface OutQuartService extends IService<OutQuart> {

    /**
     * 外部定时任务简单分页查询
     * 
     * @param outQuart
     *            外部定时任务
     * @return
     */
    IPage<OutQuart> getOutQuartPage(Page<OutQuart> page, OutQuart outQuart);

    /**
     * 保存外部定时任务信息
     * 
     * @param outQuart
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 上午11:29:37
     */
    Boolean saveOut(OutQuartVO outQuart);

    /**
     * 修改外部定时任务信息
     * 
     * @param outQuartVO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:25:36
     */
    Boolean updateOut(OutQuartVO outQuartVO);

    /**
     * 删除外部定时任务信息
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:26:36
     */
    Boolean deleteOut(String jobName, String jobGroup);
    
    /**
     * 修改任务状态
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午3:45:39
     */
    Boolean updateState(String jobName, String jobGroup);

}
