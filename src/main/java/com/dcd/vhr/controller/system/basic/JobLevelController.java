package com.dcd.vhr.controller.system.basic;

import com.dcd.vhr.model.JobLevel;
import com.dcd.vhr.model.Position;
import com.dcd.vhr.model.RespBean;
import com.dcd.vhr.service.JobLevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/basic/job")
public class JobLevelController {

    @Resource
    private JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevel(){
        return jobLevelService.getAllJobLevel();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        jobLevel.setCreatedate(new Date());
        jobLevel.setEnabled(true);
        if (jobLevelService.addJobLevel(jobLevel) == 1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.updateJobLevel(jobLevel) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if (jobLevelService.deleteJobLevel(id) == 1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
}
