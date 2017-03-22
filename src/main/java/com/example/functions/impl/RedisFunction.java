package com.example.functions.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by cjh on 2017. 3. 20..
 */
@Component
public class RedisFunction {

    private static final Logger log = LoggerFactory.getLogger(RedisFunction.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name="strRedisTemplate")
    private ListOperations<String, String> listOperations;

    @Resource(name="strRedisTemplate")
    private SetOperations<String, String> setOperations;

    //마지막 value pop
    public String pop(String user_key) {
        String value = listOperations.rightPop(user_key);
        log.debug("value : {} ", value);
        return value;
    }

    //value push
    public void push(String user_key, String value) {
        log.info("push user_key : {}, value : {}", user_key, value);
        listOperations.rightPush(user_key, value);
    }

    //key 삭제
    public void delete(String user_key) {
        redisTemplate.opsForValue().getOperations().delete(user_key);
    }


}
