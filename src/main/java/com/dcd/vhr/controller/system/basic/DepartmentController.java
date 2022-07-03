package com.dcd.vhr.controller.system.basic;

import com.dcd.vhr.model.Department;
import com.dcd.vhr.model.RespBean;
import com.dcd.vhr.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department dep){
        if (departmentService.addDepartment(dep) == 1){
            return RespBean.ok("更新成功",dep);
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id){
        Integer result = departmentService.deleteDepartment(id);
        if (result == 1){
            return RespBean.ok("删除成功");
        }else if (result == -2){//删除的部门有子部门
            return RespBean.error("删除失败,该部门下有子部门");
        }else if (result == -1){//删除的部门有员工
            return RespBean.error("删除失败,该部门下有员工");
        }else {
            return RespBean.error("删除失败");
        }

    }
}
