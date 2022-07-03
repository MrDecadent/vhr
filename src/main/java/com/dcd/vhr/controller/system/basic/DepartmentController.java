package com.dcd.vhr.controller.system.basic;

import com.dcd.vhr.model.Department;
import com.dcd.vhr.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Resource
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartmentByParentId(-1);
    }

}
