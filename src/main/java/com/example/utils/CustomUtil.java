package com.example.utils;

import com.example.dto.Paging;
import com.example.dto.Param;
import com.example.model.Redis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjh on 2017. 3. 23..
 */
public class CustomUtil {

    private static final Logger log = LoggerFactory.getLogger(CustomUtil.class);

    public static String objectToString(Redis redis) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
		try {
            jsonString = mapper.writeValueAsString(redis);
            log.debug("jsonString : {}", jsonString);
        } catch (JsonProcessingException e) {
           e.printStackTrace();
        }
        return jsonString;
    }

    public static Redis stringToObject(String str) {
        ObjectMapper mapper = new ObjectMapper();
        Redis redis = new Redis();
        try {
            redis = mapper.readValue(str, Redis.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redis;
    }

    public static Redis paramToObject(String function, Param... params) {
        Redis redis = new Redis();
        redis.setFunction(function);

        Map<String, String> map = new HashMap<>();
        for(Param param : params) {
            map.put(param.getKey(), param.getValue());
        }
        redis.setParam(map);

        return redis;
    }
}
