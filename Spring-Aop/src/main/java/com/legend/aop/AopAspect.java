package com.legend.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @className Aspect
 * @description:
 * @author legend
 * @date 2024/12/31 22:59
 * @version 1.0
 */

@Aspect
@Component
public class AopAspect {

    @Pointcut("execution(* com.legend.service.UserService.findUserInfo())")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void doBefore(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("before:开始访问接口方法");
    }

    @Around("")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){

    }
}
