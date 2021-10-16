package com.fastcampus.aop.rewind.aop;

import com.fastcampus.aop.rewind.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.fastcampus.aop.rewind.controller..*.*(..))")
    public void cut(){}

    @Pointcut("@annotation(com.fastcampus.aop.rewind.annotation.Decode)")
    public void enableDecode(){
    }

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args){
            if (arg instanceof User) {
                User user = User.class.cast(args);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if (returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }
}
