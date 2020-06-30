package com.hdd.logintest.interceptor;

import com.alibaba.fastjson.JSON;

import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.utils.ContextHolderUtil;
import com.hdd.logintest.utils.TokenRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;



/**
 * 拦截器
 * @author : zkkj
 * @date 2020/5/26 17:39
 */

@Component
public class AuthorInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenRedisUtil tokenRedisUtil;

    @Autowired
    private ContextHolderUtil contextHolderUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PrintWriter writer = null;
        try {
            String token = contextHolderUtil.getToken();
            if (StringUtils.isEmpty(token) || ObjectUtils.isEmpty(tokenRedisUtil.getToken(token, false))) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setStatus(401);
                CommonResult<String> commonResult=new CommonResult<>();
                commonResult.setCode(200);
                commonResult.setMessage("您还没有登录或登录已过期，请重新登录！");
                writer = response.getWriter();
                writer.print(JSON.toJSONString(commonResult));
                return false;
            }
        } catch (Exception e){
            return false;
        } finally {
            if (!ObjectUtils.isEmpty(writer)){
                writer.close();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex)
            throws Exception {

    }

}