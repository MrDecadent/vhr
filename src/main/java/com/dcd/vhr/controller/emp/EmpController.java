package com.dcd.vhr.controller.emp;

import com.dcd.vhr.model.*;
import com.dcd.vhr.service.EmpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/emp/basic")
public class EmpController {

    @Resource
    EmpService empService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(//默认值第一页，十条记录
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
                                          String keywords){
        return empService.getEmployeeByPage(page,size,keywords);
    }

    @PutMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        if (empService.addEmployee(employee) == 1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
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
}
