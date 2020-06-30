package com.hdd.logintest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户注册信息",description = "用户注册")
public class Users implements Serializable {


    @ApiModelProperty(value = "用户登录名称",name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户登录密码", name = "password")
    private String password;
    @ApiModelProperty(value = "用户登录密码确认", name = "passwords")
    private String passwords;

}
