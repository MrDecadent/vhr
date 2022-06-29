package com.dcd.vhr.service;

import com.dcd.vhr.mapper.JobLevelMapper;
import com.dcd.vhr.model.JobLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobLevelService {

    @Resource
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevel() {
        return jobLevelMapper.getAllJobLevel();
    }

    public Integer addJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.insertSelective(jobLevel);
    }


    public Integer deleteJobLevel(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelsByIds(Integer[] ids) {
        return jobLevelMapper.deleteJobLevelsByIds(ids);
    }
}
