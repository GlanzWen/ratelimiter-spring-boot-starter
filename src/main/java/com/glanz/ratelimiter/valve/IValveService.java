package com.glanz.ratelimiter.valve;

import com.glanz.ratelimiter.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @Author GlanzWen
 * @Description Something
 * @github
 */
public interface IValveService {

    Object access (ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable;


}
