package com.hdd.logintest.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.entity.Emp;
import com.hdd.logintest.entity.PageQueryVO;

import java.util.List;

public interface EmpService {
    /**
     * 新增员工信息
     * @param emp 员工信息
     * @return 新增结果
     */
    CommonResult<String> addEmp(Emp emp);

    /**
     * 删除员工信息
     * @param id 员工信息id
     * @return 删除结果
     */
    CommonResult<String> deleteEmp(String id);

    /**
     * 根据员工id查询员工信息
     * @param id 员工id
     * @return 员工信息
     */
    CommonResult<Emp> getEmpById(String id);

    CommonResult<List<Emp>> getEmpList(PageQueryVO page);
}
