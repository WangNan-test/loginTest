package com.hdd.logintest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.entity.User;
import com.hdd.logintest.entity.Users;
import com.hdd.logintest.mapper.UserMapper;
import com.hdd.logintest.service.ILoginService;
import com.hdd.logintest.utils.TokenRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  TokenRedisUtil tokenRedisUtil;
    @Override
    public CommonResult<String> login(User user) {
        CommonResult<String> result=new CommonResult<>();
        String username = user.getUserName().trim();
        String password = user.getPassword().trim();
        user.setUserName(username);
        user.setPassword(password);
        User login = userMapper.login(user);
        if(!ObjectUtils.isEmpty(login)){
            if(login.getPassword().equals(user.getPassword())){
                String token = tokenRedisUtil.createJWT(43200L, login, "secret", "subject");
                tokenRedisUtil.setToken(token, login, true, 2 * 60 * 60);

                result.setData(token);
                result.setMessage("登录成功");
                result.setCode(200);
                return result;
            }else{
                result.setMessage("登录失败密码错误！");
                result.setCode(404);
                return result;
            }
        }else{
            result.setMessage("登录失败当前用户不存在！");
            result.setCode(404);
            return result;
        }

    }

    @Override
    public CommonResult<String> registerUser(Users users) {
        CommonResult<String> result=new CommonResult<>();
        User user = new User();
        user.setUserName(users.getUserName().trim());
        user.setPassword(users.getPassword().trim());
        int info=this.userMapper.registerUser(user);
        if(info>0){
            result.setMessage("注册成功！");
            result.setCode(200);
            return result;
        }else{
            result.setMessage("注册失败！");
            result.setCode(404);
            return result;
        }

    }
}
