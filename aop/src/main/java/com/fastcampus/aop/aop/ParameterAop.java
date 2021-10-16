package com.fastcampus.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    //point cut : AoP를 적용하는 지점 설정 - 아래의 조건 식은 필요할 때 인터넷에서 찾아야 함
    @Pointcut("execution(* com.fastcampus.aop.controller..*.*(..))")
    private void cut(){

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName() );
            System.out.println("value : " + obj );
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("returnObj");
        System.out.println(returnObj);
    }

}
