package com.fastcampus.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    //point cut : AoP를 적용하는 지점 설정 - 아래의 조건 식은 필요할 때 인터넷에서 찾아야 함
    @Pointcut("execution(* com.fastcampus.aop.controller..*.*(..))")
    private void cut(){

    }

    @Pointcut("@annotation(com.fastcampus.aop.aop.annotation.Timer)")
    private void enableTimer(){

    }

    @Around("cut() && enableTimer() ")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
