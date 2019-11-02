package com.junfeng.platform.oc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 工具类
 *
 * @projectName:operation-center-biz
 * @author:Wangjf
 * @date:2019年9月24日 下午2:02:35
 * @version 1.0
 */
public class CronUtils {
    /**
     * 根据时间生成 定时任务cron表达式
     *
     * @param dateTime
     * @return
     * @author:Wangjf
     * @createTime:2019年9月24日 下午2:02:51
     */
    public static String getCron(LocalDateTime dateTime) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;

    }

    /**
     * cronExpression加减时间
     *
     * @author: wangjf
     * @createTime: 2019/10/30 11:17
     * @param cronExpression
     * @param min
     * @return java.lang.String
     */
    public static String getCron(String cronExpression, Integer min) {

        String dateFormat = "ss mm HH dd MM ? yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime dateTime = LocalDateTime.parse(cronExpression, formatter);
        LocalDateTime localDateTime = dateTime.plusMinutes(min);
        return getCron(localDateTime);
    }

}
