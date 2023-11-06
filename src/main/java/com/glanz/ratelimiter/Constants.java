package com.glanz.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author GlanzWen
 * @Description Something
 * @github
 */
public class Constants {

    public static Map<String, RateLimiter> rateLimiterMap = Collections.synchronizedMap(new HashMap<String, RateLimiter>());

}
