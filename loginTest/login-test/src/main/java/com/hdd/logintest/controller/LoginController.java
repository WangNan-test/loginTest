package com.hdd.logintest.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.entity.User;
import com.hdd.logintest.entity.Users;
import com.hdd.logintest.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "LoginController", tags = "{登录API}")
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private ILoginService loginService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public CommonResult<String> login(@RequestBody @ApiParam(value = "用户登录模型", name = "user", required = true) User user) {
        CommonResult<String> commonResult = new CommonResult<>();
        if (ObjectUtils.isEmpty(user)) {
            commonResult.setCode(404);
            commonResult.setMessage("登录失败！");
            return commonResult;
        }

        if (StringUtils.isEmpty(user.getUserName()) || user.getUserName().length() <= 0) {
            commonResult.setCode(404);
            commonResult.setMessage("登录失败，用户名不能为null");
            return commonResult;
        }
        if (StringUtils.isEmpty(user.getPassword()) || user.getPassword().length() <= 0) {
            commonResult.setCode(404);
            commonResult.setMessage("登录失败，密码不能为null");
            return commonResult;
        }

        try {
            commonResult = loginService.login(user);
            return commonResult;
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(404);
            commonResult.setMessage("登录失败请重试");
            return commonResult;
        }
    }
    @PostMapping("/registerUser")
    @ApiOperation(value = "用户注册接口")
    public CommonResult<String>  registerUser(@RequestBody @ApiParam(value = "用户模型", name = "user", required = true) Users users){
        CommonResult<String> result = new  CommonResult<>();
        if(StringUtils.isEmpty(users.getUserName().trim())|| StringUtils.isEmpty(users.getPassword().trim())||StringUtils.isEmpty(users.getPasswords().trim())){
            result.setMessage("所有参数不能为空");
            result.setCode(404);
            return result;
        }
        if(!users.getPassword().trim().equals(users.getPasswords().trim())){
            result.setMessage("两次密码不一致！请重新输入");
            result.setCode(404);
            return result;
        }
        try {
            result=this.loginService.registerUser(users);
            return result;
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("系统异常请重试");
            return result;
        }
    }


}
