package com.dcd.vhr.service;

import com.dcd.vhr.mapper.EmployeeMapper;
import com.dcd.vhr.model.Employee;
import com.dcd.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpService {
    @Resource
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page-1)*size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page,size);
        Long total = employeeMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
