package com.dcd.vhr.controller.emp;

import com.dcd.vhr.model.*;
import com.dcd.vhr.service.DepartmentService;
import com.dcd.vhr.service.EmpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpController {

    @Resource
    EmpService empService;
    @Resource
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(//默认值第一页，十条记录
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
                                          String keywords,
                                          Employee employee,
                                          Date[] begindatescope){
        return empService.getEmployeeByPage(page,size,keywords,employee,begindatescope);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        if (empService.addEmployee(employee) == 1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmployeeById(@PathVariable Integer id){
        if (empService.deleteEmployeeById(id) == 1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee){
        if (empService.updateEmployee(employee) == 1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return empService.getAllNations();
    }

    @GetMapping("/politic")
    public List<Politicsstatus> getAllPolitic(){
        return empService.getAllPolitic();
    }

    @GetMapping("/jobLevels")
    public List<JobLevel> getAllJobLevels(){
        return empService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return empService.getAllPositions();
    }

    @GetMapping("/MaxWorkId")
    public RespBean getMaxWorkId(){
        return RespBean.build().setStatus(200).setObject(String.format("%08d", empService.getMaxWorkId()+1));
    }

    @GetMapping("/department")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartmentByParentId(-1);
    }
}
