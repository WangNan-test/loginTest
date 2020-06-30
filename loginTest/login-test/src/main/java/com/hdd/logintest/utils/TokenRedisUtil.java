package com.hdd.logintest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.hdd.logintest.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Token工具类
 * @author : zkkj
 * @date 2020/5/26 15:25
 */
@Service
public class TokenRedisUtil {

    @Autowired
    private StringRedisUtil stringRedisUtil;

    /**
     * 设置token值
     * @param key
     * @param o
     * @param isExpireSecond
     */
    public void setToken(String key, Object o, boolean isExpireSecond) {
        String s = JSON.toJSONString(o);
        this.stringRedisUtil.setKey(key, s, isExpireSecond);
    }

    /**
     * 设置token值（有效时间）
     * @param key
     * @param o
     * @param isExpireSecond
     * @param time
     */
    public void setToken(String key, Object o, boolean isExpireSecond, long time) {
        String s = JSON.toJSONString(o);
        this.stringRedisUtil.setKey(key, s, isExpireSecond, time);
    }

    /**
     * 获取token值
     * @param key
     * @param isExtension
     * @return
     */
    public JSONObject getToken(String key, boolean isExtension) {
        String s = this.stringRedisUtil.getValue(key, isExtension);
        return JSONObject.parseObject(s);
    }

    /**
     * 创建token字符串
     * @param ttlMillis
     * @param user
     * @param secret
     * @param subject
     * @return
     */
    public String createJWT(long ttlMillis, User user, String secret, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginAccount", user.getUserName());
        JwtBuilder builder = Jwts.builder().setClaims(claims).setId(UUID.randomUUID().toString()).setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm, secret);
        if (ttlMillis >= 0L) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

}
