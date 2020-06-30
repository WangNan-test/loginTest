package com.hdd.logintest.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.entity.User;
import com.hdd.logintest.entity.Users;

/**
 * 登录Service
 */
public interface ILoginService {

    /**
     * 用户登录操作
     * @param user 用户信息
     * @return 用户登录结果
     */
    CommonResult<String> login(User user);

    /**
     * 用户注册操作
     * @param users
     * @return
     */
    CommonResult<String> registerUser(Users users);
}
