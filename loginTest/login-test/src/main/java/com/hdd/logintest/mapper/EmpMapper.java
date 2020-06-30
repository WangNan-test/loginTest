package com.hdd.logintest.mapper;

import com.hdd.logintest.entity.Emp;
import com.hdd.logintest.entity.PageQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 新增用户信息
     * @param emp 用户信息
     * @return 用户新增ID
     */
    int addEmp(Emp emp);

    /**
     * 删除员工信息
     * @param id    用户id信息
     * @return
     */
    int deleteEmp(String id);

    /**
     * 根据员工ID查询
     * @param id 员工id
     * @return
     */
    Emp getEmpById(String id);

    List<Emp> getEmpList(PageQueryVO page);
}
