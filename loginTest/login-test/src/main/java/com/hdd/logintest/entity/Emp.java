package com.hdd.logintest.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "员工模型",description = "员工表")
public class Emp implements Serializable {
    @ApiModelProperty(value = "员工id",name="id")
    private Long id;
    @ApiModelProperty(value = "员工姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "员工年龄",name = "name")
    private Integer age;
    @ApiModelProperty(value = "员工地址",name = "address")
    private String address;
    @ApiModelProperty(value = "删除标记",name = "mark")
    private Integer mark;
}
