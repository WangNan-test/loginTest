package com.hdd.logintest.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.github.pagehelper.PageHelper;
import com.hdd.logintest.entity.Emp;
import com.hdd.logintest.entity.PageQueryVO;
import com.hdd.logintest.mapper.EmpMapper;
import com.hdd.logintest.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public CommonResult<String> addEmp(Emp emp) {
        CommonResult<String> result=new CommonResult<>();
        int id=empMapper.addEmp(emp);
        if(id>0){
            result.setMessage("新增用户成功");
            result.setCode(200);
            return result;
        }else{
            result.setMessage("新增用户失败");
            result.setCode(404);
            return result;
        }
    }

    @Override
    public CommonResult<String> deleteEmp(String id) {
        CommonResult<String> result=new CommonResult<>();
        int info=empMapper.deleteEmp(id);
        if(info>0){
            result.setMessage("删除成功");
            result.setCode(200);
            return result;
        }else {
            result.setMessage("删除失败");
            result.setCode(404);
            return result;
        }
    }

    @Override
    public CommonResult<Emp> getEmpById(String id) {
        CommonResult<Emp> result=new CommonResult<>();
        Emp emp=this.empMapper.getEmpById(id);
        if(ObjectUtils.isEmpty(emp)){
            result.setMessage("该用户信息不存在或已删除！");
            result.setCode(404);
            return result;
        }else{
            result.setMessage("查询成功");
            result.setData(emp);
            result.setCode(200);
            return result;
        }
    }

    @Override
    public CommonResult<List<Emp>> getEmpList(PageQueryVO page) {
        CommonResult<List<Emp>> result=new CommonResult<>();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Emp> list=this.empMapper.getEmpList(page);
        if(CollectionUtils.isEmpty(list)){
            result.setMessage("查询不到当前员工信息");
            result.setCode(404);
            return result;
        }else{
            result.setCode(200);
            result.setMessage("查询成功");
            result.setData(list);
            return result;
        }
    }
}
