package com.junfeng.platform.qc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.constant.QuartzCenterConstant;
import com.junfeng.platform.qc.entity.OutQuart;
import com.junfeng.platform.qc.mapper.OutQuartMapper;
import com.junfeng.platform.qc.service.OutQuartService;
import com.junfeng.platform.qc.service.SchedulerService;
import com.junfeng.platform.qc.util.CronUtils;
import com.junfeng.platform.qc.util.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * 外部定时任务
 *
 * @author wangjf
 * @date 2019-09-25 10:49:16
 */
@Service
@Slf4j
public class OutQuartServiceImpl extends ServiceImpl<OutQuartMapper, OutQuart> implements OutQuartService {

    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private RestTemplate lbRestTemplate;

    /**
     * 外部定时任务简单分页查询
     *
     * @param outQuart
     *            外部定时任务
     * @return
     */
    @Override
    public IPage<OutQuart> getOutQuartPage(Page<OutQuart> page, OutQuart outQuart) {
        return baseMapper.getOutQuartPage(page, outQuart);
    }

    /**
     * 保存外部定时任务
     *
     * @param outQuartVO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 上午11:30:18
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean saveOut(OutQuartVO outQuartVO) {
        outQuartVO.setJobClassName(QuartzCenterConstant.DEFAULT_JOB_CLASS_NAME);
        log.info("outQuartVO={}", outQuartVO);
        // 保存外部定时任务信息
        OutQuart outQuart = new OutQuart();
        BeanUtil.copyProperties(outQuartVO, outQuart, CopyOptions.create().setIgnoreNullValue(true));
        save(outQuart);

        schedulerService.createOutJob(outQuartVO);
        return Boolean.TRUE;
    }

    /**
     * 修改定时任务
     *
     * @param outQuartVO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:28:44
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean updateOut(OutQuartVO outQuartVO) {
        outQuartVO.setJobClassName(QuartzCenterConstant.DEFAULT_JOB_CLASS_NAME);
        // 先删除信息
        schedulerService.deleteJob(outQuartVO.getJobName(), outQuartVO.getJobGroup());
        baseMapper.delete(Wrappers.<OutQuart> query().lambda().eq(OutQuart::getJobName, outQuartVO.getJobName())
                .eq(OutQuart::getJobGroup, outQuartVO.getJobGroup())
                .eq(OutQuart::getOutSysName, outQuartVO.getOutSysName()));
        // 保存外部定时任务信息
        OutQuart outQuart = new OutQuart();
        BeanUtil.copyProperties(outQuartVO, outQuart, CopyOptions.create().setIgnoreNullValue(true));
        save(outQuart);
        schedulerService.createOutJob(outQuartVO);
        return Boolean.TRUE;

    }

    /**
     * 删除定时任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:28:58
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean deleteOut(String jobName, String jobGroup) {
        schedulerService.deleteJob(jobName, jobGroup);
        baseMapper.delete(Wrappers.<OutQuart> query().lambda().eq(OutQuart::getJobName, jobName)
                .eq(OutQuart::getJobGroup, jobGroup));
        return Boolean.TRUE;
    }

    /**
     * 更新任务状态
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午3:47:52
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateState(String jobName, String jobGroup) {

        OutQuart qutQuart = baseMapper.selectOne(Wrappers.<OutQuart> query().lambda().eq(OutQuart::getJobName, jobName)
                .eq(OutQuart::getJobGroup, jobGroup));

        if (qutQuart == null) {
            return Boolean.FALSE;
        }

        try {
            lbRestTemplate.postForObject(qutQuart.getCallbackUrl(), HttpUtil.getInnerHttpHead(), String.class);
        } catch (Exception e) {
            if (qutQuart.getCallbackCount() < 3) {
                OutQuartVO outQuartVO = new OutQuartVO();
                BeanUtil.copyProperties(qutQuart, outQuartVO);
                try {
                    outQuartVO.setCronExpression(CronUtils.getCron(CronUtils.getCron(LocalDateTime.now()),
                            (qutQuart.getCallbackCount() + 1) * 5));
                    outQuartVO.setOldJobGroup(qutQuart.getJobGroup());
                    outQuartVO.setOldJobName(qutQuart.getJobName());
                    schedulerService.createOutJob(outQuartVO);
                } catch (Exception e1) {
                    log.error("调用quartz定时器失败={},异常信息{}", qutQuart, e);
                }
            }

        }
        OutQuart updateObj = new OutQuart();
        updateObj.setId(qutQuart.getId());
        updateObj.setState("1");
        updateObj.setCallbackCount(qutQuart.getCallbackCount() + 1);
        updateById(updateObj);
        return Boolean.TRUE;

    }

}
