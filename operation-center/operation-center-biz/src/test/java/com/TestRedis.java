package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/28 13:37
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class TestRedis {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Test
    public void test() {

        System.out.println(redisConnectionFactory.getConnection());

    }

}
