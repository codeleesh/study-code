package com.lovethefeel.springboot.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = proceedingJoinPoint.proceed();
        String className = proceedingJoinPoint.getSignature().getName();

        stopWatch.stop();
        //logger.info(stopWatch.prettyPrint());
        logger.info("{} elapsed time :: {}", className, stopWatch.getTotalTimeMillis());

        return proceed;
    }
}
