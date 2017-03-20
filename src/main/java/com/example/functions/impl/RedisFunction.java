package com.example.functions.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
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

    @Resource(name="strRedisTemplate")
    private ListOperations<String, String> listOperations;

    @Resource(name="strRedisTemplate")
    private SetOperations<String, String> setOperations;

    public String getUserLastAction(String user_key) {
        //TODO
        listOperations.rightPush("123", "value value");
        String value = listOperations.leftPop("123");
        log.debug("value : {} ", value);
        return value;
    }
}
