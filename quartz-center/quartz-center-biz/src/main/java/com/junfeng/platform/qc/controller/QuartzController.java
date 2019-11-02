package com.junfeng.platform.qc.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.qc.entity.QuartzEntity;
import com.junfeng.platform.qc.service.QuartzService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import lombok.AllArgsConstructor;

/**
 * 任务接口类
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 上午11:08:21
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/quartz")
public class QuartzController {

    private final QuartzService quartzService;

    /**
     * 通过任务名称和任务组获取任务详情
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 下午3:44:34
     */
    @GetMapping("/{jobName}/{jobGroup}")
    public R<QuartzEntity> getById(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup) {

        return R.ok(quartzService.get(jobName,jobGroup));
    }

    /**
     * 添加任务
     * 
     * @param quartzDTO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 下午3:42:14
     */
    @SysLog("添加任务")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('quartz_add')")
    public R<Boolean> save(@Valid @RequestBody QuartzEntity quartzEntity) {
        Boolean result = false;

        try {
            result = quartzService.saveQuartz(quartzEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(result);
        }
        return R.ok(result);
    }
    
    /**
     * 添加外部定时任务接口
     * @param quartzDTO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月24日 下午5:11:15
     */
    @Inner
    @SysLog("添加外部任务")
    @PostMapping("/out")
    public R<Boolean> saveOutTask(@RequestBody QuartzEntity quartzEntity) {

        Boolean result = false;

        try {
            result = quartzService.saveQuartz(quartzEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(result);
        }
        return R.ok(result);
    }
    

    /**
     * 修改任务
     * 
     * @param quartzDTO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 下午3:47:00
     */
    @SysLog("修改任务")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('quartz_edit')")
    public R<Boolean> update(@Valid @RequestBody QuartzEntity quartzEntity) {

        Boolean result = false;

        try {
            result = quartzService.saveQuartz(quartzEntity);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed(result);
        }
        return R.ok(result);
    }

    /**
     * 删除任务
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 下午3:47:19
     */
    @SysLog("删除任务")
    @DeleteMapping("/{jobName}/{jobGroup}")
    @PreAuthorize("@pms.hasPermission('quartz_del')")
    public R<Boolean> remove(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup) {

        try {
            quartzService.removeQuartz(jobName, jobGroup);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed(false);
        }
        return R.ok(true);
    }

    /**
     * 立即执行
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:15:55
     */
    @SysLog("立即执行")
    @PutMapping("/trigger/{jobName}/{jobGroup}")
    @PreAuthorize("@pms.hasPermission('quartz_trigger')")
    public R<Boolean> trigger(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup) {

        try {
            quartzService.trigger(jobName, jobGroup);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed(false);
        }
        return R.ok(true);
    }

    /**
     * 暂停任务
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:18:16
     */
    @SysLog("暂停任务")
    @PutMapping("/pause/{jobName}/{jobGroup}")
    @PreAuthorize("@pms.hasPermission('quartz_pause')")
    public R<Boolean> pause(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup) {

        try {
            quartzService.pause(jobName, jobGroup);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed(false);
        }
        return R.ok(true);
    }

    /**
     * 重启任务
     * 
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:18:26
     */
    @SysLog("任务重启")
    @PutMapping("/resume/{jobName}/{jobGroup}")
    @PreAuthorize("@pms.hasPermission('quartz_resume')")
    public R<Boolean> resume(@PathVariable("jobName") String jobName, @PathVariable("jobGroup") String jobGroup) {

        try {
            quartzService.resume(jobName, jobGroup);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed(false);
        }
        return R.ok(true);
    }


    /**
     * 分页查询
     * 
     * @param page
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 下午3:47:49
     */
    @GetMapping("/page")
    public R<IPage<QuartzEntity>> getPage(Page page, QuartzEntity quartzEntity) {

        return R.ok(quartzService.getQuartzPage(page, quartzEntity));
    }

}
