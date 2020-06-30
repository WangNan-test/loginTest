package com.hdd.logintest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author : zkkj
 * @date 2020/5/26 13:34
 */
@ApiModel(
        value = "分页前端传入参数封装类",
        description = "分页前端传入参数封装类"
)
public class PageQueryVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            value = "当前页数",
            name = "pageNum",
            example = "1"
    )
    private int pageNum;

    @ApiModelProperty(
            value = "分页条数",
            name = "pageSize",
            example = "10"
    )
    private int pageSize;

    @ApiModelProperty(
            value = "传入参数",
            name = "data"
    )
    private T data;

    public PageQueryVO() {
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public T getData() {
        return this.data;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageQueryVO(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", data=" + this.getData() + ")";
    }
}
