package com.example.functions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cjh on 2017. 3. 20..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisFunctionTest {

    private final String user_key = "123";

    @Autowired
    private RedisFunction redisFunction;

    @Test
    public void pop() throws Exception {
        redisFunction.pop(user_key);
    }

    @Test
    public void push() throws Exception {
        for(int i = 0 ; i < 3; i ++) {
            redisFunction.push(user_key, "value : " + i);
        }
    }

    @Test
    public void delete() throws Exception {
        redisFunction.delete(user_key);
    }

    @Test
    public void getLastValue() throws Exception {
        redisFunction.getLastValue(user_key);
    }
}