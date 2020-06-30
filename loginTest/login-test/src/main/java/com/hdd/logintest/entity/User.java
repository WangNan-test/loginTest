package com.hdd.logintest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户登录表",description = "用户登录表")
public class User implements Serializable {
    @ApiModelProperty(value = "用户登录id",name = "id")
    private Long id;

    @ApiModelProperty(value = "用户登录名称",name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户登录密码", name = "password")
    private String password;


}
