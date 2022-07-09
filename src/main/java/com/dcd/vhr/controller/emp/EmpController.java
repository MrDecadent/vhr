package com.dcd.vhr.controller.emp;

import com.dcd.vhr.model.RespPageBean;
import com.dcd.vhr.service.EmpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
