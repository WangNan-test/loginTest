package com.hdd.logintest.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.hdd.logintest.entity.Emp;
import com.hdd.logintest.entity.PageQueryVO;
import com.hdd.logintest.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Api(value = "EmpController", tags = "{员工控制层}")
@RequestMapping("/emp")
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/addEmp")
    @ApiOperation(value = "员工新增接口")
    public CommonResult<String> addEmp(@RequestBody @ApiParam(value = "员工模型", name = "emp", required = true) Emp emp) {
        CommonResult<String> commonResult = new CommonResult<>();
        if (ObjectUtils.isEmpty(emp)) {
            commonResult.setMessage("新增失败！");
            return commonResult;
        }
        try {
            commonResult = empService.addEmp(emp);
            return commonResult;
        } catch (Exception e) {
            commonResult.setMessage("系统异常请重试！");
            log.info("EmpController addEmp " + e.getMessage());
        }
        return commonResult;
    }

    @GetMapping("/deleteEmp")
    @ApiOperation(value = "员工删除接口")
    public CommonResult<String> deleteEmp(@RequestParam @ApiParam(value = "员工信息ID", name = "id", required = true) String id) {
        CommonResult<String> commonResult = new CommonResult<>();
        if (StringUtils.isEmpty(id)) {
            commonResult.setMessage("删除失败！");
            return commonResult;
        }
        try {
            commonResult = empService.deleteEmp(id);
            return commonResult;
        } catch (Exception e) {
            commonResult.setMessage("系统异常请重试！");
            log.info("EmpController addEmp " + e.getMessage());
        }
        return commonResult;
    }
    @GetMapping("/getEmpById")
    @ApiOperation(value = "根据id查询员工信息")
    public CommonResult<Emp> getEmpById(@RequestParam @ApiParam(value = "员工ID", name = "id", required = true) String id) {
        CommonResult<Emp> result = new CommonResult<Emp>();
        if (StringUtils.isEmpty(id) && !id.matches("\\d+")) {
            result.setMessage("请输入正确的员工ID");
            return result;
        }
        try {
            result=this.empService.getEmpById(id);
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMessage("系统异常请重试");
            return result;
        }
    }
    @PostMapping("/getEmpList")
    @ApiOperation(value = "查询公司员工信息")
    public  CommonResult<List<Emp>>  getEmpList(@RequestBody @ApiParam(value = "查询员工信息列表模型",name = "page",required = true)
                                                PageQueryVO page){
        CommonResult<List<Emp>> result = new CommonResult<>();

        try {
            result=this.empService.getEmpList(page);
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMessage("系统异常请重试");
            return result;
        }
    }
}
