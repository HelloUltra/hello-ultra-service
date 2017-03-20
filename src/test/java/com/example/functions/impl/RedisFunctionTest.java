package com.example.functions.impl;

import com.example.MessageDispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by cjh on 2017. 3. 20..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisFunctionTest {


    @Autowired
    private RedisFunction redisFunction;

    @Test
    public void getUserLastAction() {
        System.out.println(redisFunction.getUserLastAction("123"));
    }
}