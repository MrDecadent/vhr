package com.dcd.vhr.controller.system;

import com.dcd.vhr.model.Hr;
import com.dcd.vhr.model.RespBean;
import com.dcd.vhr.service.HrService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Resource
    HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrs(){
        return hrService.getAllHrs();
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr){
        if (hrService.updateHr(hr) == 1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }
}
