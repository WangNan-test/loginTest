package com.hdd.logintest.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 * @author : zkkj
 * @date 2020/5/26 17:39
 */
@Configuration
public class AuthorInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthorInterceptor authorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(authorInterceptor).addPathPatterns("/**")
                //不拦截 静态资源、swagger、 等登录相关
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-ui.html",
                        "/swagger**",
                        "/v2/**",
                        "/images/**",
                        "/",
                        "/login/login",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.jpg",
                        "/**/*.jpeg",
                        //平台用户登录
                        "/login/userlogin",
                        "/login/registerUser",
                        //地图用户登录
                        "/platformLogin/loginByTerminals",
                        "/platformLogin/loginByPlateNums",
                        "/foreignTerminal/*"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}