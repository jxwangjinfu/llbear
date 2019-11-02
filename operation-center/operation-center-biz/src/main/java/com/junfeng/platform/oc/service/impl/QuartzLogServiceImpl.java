package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.constant.OperationCenterConstant;
import com.junfeng.platform.oc.entity.QuartzLog;
import com.junfeng.platform.oc.mapper.QuartzLogMapper;
import com.junfeng.platform.oc.service.*;
import com.junfeng.platform.oc.util.CronUtils;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 定时任务触发日志表
 *
 * @author wangjf
 * @date 2019-10-15 14:55:08
 */
@Slf4j
@Service
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogMapper, QuartzLog> implements QuartzLogService {
    @Autowired
    private RemoteQuartzService remoteQuartzService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private BuyGiftService buyGiftService;
    @Autowired
    private RedEnvelopeService redEnvelopeService;
    @Autowired
    private GiftService giftService;
    @Autowired
    private GroupBuyService groupBuyService;

    /**
     * 定时任务触发日志表简单分页查询
     *
     * @param quartzLog
     *            定时任务触发日志表
     * @return
     */
    @Override
    public IPage<QuartzLog> getQuartzLogPage(Page<QuartzLog> page, QuartzLog quartzLog) {
        return baseMapper.getQuartzLogPage(page, quartzLog);
    }

    /**
     * 发送定时任务至qc系统中
     *
     * @param quartzLog
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 15:00
     */
    @Override
    public Boolean sendQcQuartz(QuartzLog quartzLog, LocalDateTime executeDateTime) {

        String jobName = OperationCenterConstant.SERVICE_NAME + "-" + quartzLog.getOcQuartzType() + "-"
                + System.currentTimeMillis();
        quartzLog.setJobGroup(jobName);
        quartzLog.setJobName(jobName);
        save(quartzLog);

        OutQuartVO outQuartVO = new OutQuartVO();
        outQuartVO.setOutSysName(OperationCenterConstant.SERVICE_NAME);
        outQuartVO.setCallbackUrl(OperationCenterConstant.QUARTZ_CALLBACK_URL + quartzLog.getOcQuartzId());
        outQuartVO.setCronExpression(CronUtils.getCron(executeDateTime));
        outQuartVO.setJobGroup(jobName);
        outQuartVO.setJobName(jobName);
        outQuartVO.setTriggerName("trigger" + System.currentTimeMillis());
        outQuartVO.setParam(quartzLog.getOcQuartzType() + "-" + quartzLog.getOcQuartzId());
        remoteQuartzService.saveOut(outQuartVO, SecurityConstants.FROM_IN);
        return Boolean.TRUE;
    }

    /**
     * 发送定时任务至qc系统中
     *
     * @param quartzId
     * @param quartzType
     * @param quartzState
     * @param executeDateTime
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 15:41
     */
    @Override
    public Boolean sendQcQuartz(Long quartzId, Integer quartzType, Integer quartzState, LocalDateTime executeDateTime) {
        String jobName = OperationCenterConstant.SERVICE_NAME + "-" + quartzType + "-" + System.currentTimeMillis();
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setOcQuartzId(quartzId);
        quartzLog.setOcQuartzType(quartzType);
        quartzLog.setOcQuartzState(quartzState);
        quartzLog.setJobGroup(jobName);
        quartzLog.setJobName(jobName);
        save(quartzLog);

        OutQuartVO outQuartVO = new OutQuartVO();
        outQuartVO.setOutSysName(OperationCenterConstant.SERVICE_NAME);
        outQuartVO.setCallbackUrl(OperationCenterConstant.QUARTZ_CALLBACK_URL + quartzLog.getId());
        outQuartVO.setCronExpression(CronUtils.getCron(executeDateTime));
        outQuartVO.setJobGroup(jobName);
        outQuartVO.setJobName(jobName);
        outQuartVO.setTriggerName("trigger" + System.currentTimeMillis());
        outQuartVO.setParam(quartzLog.getOcQuartzType() + "-" + quartzLog.getOcQuartzId());
        remoteQuartzService.saveOut(outQuartVO, SecurityConstants.FROM_IN);
        return Boolean.TRUE;
    }

    /**
     * 修改状态
     *
     * @param id
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 15:24
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateState(Long id) {

        QuartzLog quartzLog = getById(id);
        if (quartzLog == null) {
            return false;
        }
        QuartzLog updateObj = new QuartzLog();
        updateObj.setId(id);
        // 1代表任务已经回调
        updateObj.setState("1");
        baseMapper.updateById(updateObj);

        // 根据类型判断，执行相应业务方法
        if (quartzLog.getOcQuartzType() == QuartzTypeEnum.COUPON.getCode()) {
            couponService.updateState(quartzLog.getOcQuartzId(), quartzLog.getOcQuartzState());
        } else if (quartzLog.getOcQuartzType() == QuartzTypeEnum.REDENVELOPE.getCode()) {
            redEnvelopeService.updateState(quartzLog.getOcQuartzId(), quartzLog.getOcQuartzState());
        } else if (quartzLog.getOcQuartzType() == QuartzTypeEnum.GIFT.getCode()) {
            giftService.updateState(quartzLog.getOcQuartzId(), quartzLog.getOcQuartzState());
        } else if (quartzLog.getOcQuartzType() == QuartzTypeEnum.BUY.getCode()) {
            buyGiftService.updateState(quartzLog.getOcQuartzId(), quartzLog.getOcQuartzState());
        } else if (quartzLog.getOcQuartzType() == QuartzTypeEnum.GROUPBUY.getCode()) {
            groupBuyService.updateState(quartzLog.getOcQuartzId(), quartzLog.getOcQuartzState());
        } else {
            log.error("quartz 回调无法处理的类型，ID=", id);
        }
        return Boolean.TRUE;
    }

}
