package com;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.entity.PointsConfig;
import com.junfeng.platform.oc.service.PointsConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/22 11:22
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class PointsConfigServiceTest {

    @Autowired
    private PointsConfigService pointsConfigService;

    @Test
    public void test() {

		ReentrantLock ReentrantLock = new ReentrantLock();

        List<PointsConfig> list = pointsConfigService.list(Wrappers.<PointsConfig> query().lambda().between(
                PointsConfig::getCreateTime, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.format(DateUtil.offsetDay(new Date(), 1), "yyyy-MM-dd HH:mm:ss")));
        list.forEach(item -> {
            System.out.println(item);
        });

    }

}
