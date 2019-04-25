package com.mercan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
    @Around("@annotation(com.mercan.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.error("This is really important point. In this place " +
                "method started to execute");
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.error(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
