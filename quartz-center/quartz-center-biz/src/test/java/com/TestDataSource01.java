/**
 * 
 */
package com;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.qc.QuartzCenterApplication;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 下午3:24:52
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class TestDataSource01 {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Test
    public void test() {
        
        // 获取配置的数据源
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 查看配置数据源信息
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from sys_job");
        
    }

}
