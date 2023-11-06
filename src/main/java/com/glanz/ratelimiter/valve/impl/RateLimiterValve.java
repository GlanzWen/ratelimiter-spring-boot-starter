package com.glanz.ratelimiter.valve.impl;


import com.alibaba.fastjson.JSON;
import com.glanz.ratelimiter.Constants;
import com.glanz.ratelimiter.annotation.DoRateLimiter;
import com.glanz.ratelimiter.valve.IValveService;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Author GlanzWen
 * @Description Something
 * @github
 */
public class RateLimiterValve implements IValveService {


    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        //判断是否开启
        if (doRateLimiter.permitsPerSecond() == 0) return jp.proceed();

        String clazzName = jp.getTarget().getClass().getName();
        String methodName = method.getName();

        String key = clazzName + "." + methodName;

        if (Constants.rateLimiterMap.get(key) == null) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);

        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }

        return JSON.parseObject(doRateLimiter.returnJson(), method.getReturnType());
    }
}
