package com.example.SecondHomework.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("@annotation(com.example.SecondHomework.annotations.Loggable)")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        log.atInfo().log("Method: " + joinPoint.getSignature() + " Args: " + Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }
}
