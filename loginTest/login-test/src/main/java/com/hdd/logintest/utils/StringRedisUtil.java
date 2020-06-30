package com.hdd.logintest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 * @author : zkkj
 * @date 2020/5/26 15:22
 */
@Service
public class StringRedisUtil {

    @Autowired
    private StringRedisTemplate template;

    private final Long expireSecond = 10800L;

    public StringRedisUtil() {
    }

    public void setKey(String key, String value, boolean isExpireSecond) {
        if (isExpireSecond) {
            this.template.opsForValue().set(key, value, this.expireSecond, TimeUnit.SECONDS);
        } else {
            this.template.opsForValue().set(key, value);
        }
    }

    public void setKey(String key, String value, boolean isExpireSecond, long time) {
        if (isExpireSecond) {
            this.template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            this.template.opsForValue().set(key, value);
        }
    }

    public String getValue(String key, boolean isExtension, long time) {
        String result = (String)this.template.opsForValue().get(key);
        if (!isExtension) {
            return result;
        } else {
            this.template.expire(key, time, TimeUnit.SECONDS);
            return result;
        }
    }

    public String getValue(String key, boolean isExtension) {
        String result = (String)this.template.opsForValue().get(key);
        if (!isExtension) {
            return result;
        } else {
            this.template.expire(key, this.expireSecond, TimeUnit.SECONDS);
            return result;
        }
    }

    public void deleteValue(String key) {
        this.template.delete(key);
    }

}
