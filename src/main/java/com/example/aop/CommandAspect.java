package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by cjh on 2017. 4. 4..
 */
@Component
@Aspect
public class CommandAspect {

    private static final Logger log = LoggerFactory.getLogger(CommandAspect.class);

    @Around("@annotation(com.example.annotations.Command)")
    public Object redisCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("redisCheck start go go~");

        return joinPoint.proceed();
    }

}
