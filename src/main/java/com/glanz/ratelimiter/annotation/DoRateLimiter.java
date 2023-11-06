package com.glanz.ratelimiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author GlanzWen
 * @Description 注解类
 * @github
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoRateLimiter {
    double permitsPerSecond() default 0D; //限流

    String returnJson() default "";       // 失败后返回
}
