package com.dcd.vhr.service;

import com.dcd.vhr.mapper.EmployeeMapper;
import com.dcd.vhr.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpService {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    NationService nationService;
    @Resource
    politicsStatusService politicsStatusService;
    @Resource
    JobLevelService jobLevelService;
    @Resource
    PositionService positionService;

    public RespPageBean getEmployeeByPage(Integer page, Integer size,String keywords) {
        if (page != null && size != null){
            page = (page-1)*size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size,keywords);
        Long total = employeeMapper.getTotal(keywords);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    public List<Politicsstatus> getAllPolitic() {
        return politicsStatusService.getAllPolitic();
    }

    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevel();
    }

    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    public Integer getMaxWorkId() {
        return employeeMapper.getMaxWorkId();
    }

    public Integer deleteEmployeeById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }
}
