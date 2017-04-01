package com.example.config;

import com.example.dto.MessageRequest;
import com.example.dto.MessageResponse;
import com.example.functions.impl.RedisFunction;
import com.example.model.ConversationInfo;
import com.example.utils.CustomUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 3. 19..
 */
@Aspect
@Component
public class AspectConfig {

    private static final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    @Autowired
    private RedisFunction redisFunction;

    //최상위 예약어 처리 ex) 뒤로, 다시
    @Around("execution(* com..*MessageDispatcher.redisDispatch(..))")
    private Object checkReservedWords(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("AOP checkReservedWords start");

        Object[] args = joinPoint.getArgs();
        MessageRequest messageRequest = (MessageRequest) args[0];

        String resultMsg = null;
        switch (messageRequest.getContent()) {
            case "뒤로" : //redis pop 한 후 메소드 호출
                //TODO
                String value = redisFunction.pop(messageRequest.getUser_key());

                if(value == null) {
                    break;
                }

                ConversationInfo redis = CustomUtil.stringToObject(value);
                log.debug("뒤로 -> function : {}, param : {}", redis.getFunction(), redis.getParam().toString());
                break;
            case "다시" : //redis 모두 삭제
                redisFunction.delete(messageRequest.getUser_key());
                return new MessageResponse(resultMsg, null, null);
            default :
                return joinPoint.proceed();
        }

        return new MessageResponse(resultMsg, null, null);
    }
}
