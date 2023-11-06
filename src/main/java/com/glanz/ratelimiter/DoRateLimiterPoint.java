package com.glanz.ratelimiter;

import com.glanz.ratelimiter.annotation.DoRateLimiter;
import com.glanz.ratelimiter.valve.IValveService;
import com.glanz.ratelimiter.valve.impl.RateLimiterValve;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author GlanzWen
 * @Description Something
 * @github
 */

@Aspect
@Component
public class DoRateLimiterPoint {
    @Pointcut("@annotation(com.glanz.ratelimiter.annotation.DoRateLimiter)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(doRateLimiter)")
    public Object doRouter(ProceedingJoinPoint jp, DoRateLimiter doRateLimiter) throws Throwable {
        IValveService iValveService = new RateLimiterValve();
        return iValveService.access(jp, getMethod(jp), doRateLimiter, jp.getArgs());
    }

    public Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }


}
