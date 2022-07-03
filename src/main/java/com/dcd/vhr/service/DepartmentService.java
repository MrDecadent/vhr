package com.dcd.vhr.service;

import com.dcd.vhr.mapper.DepartmentMapper;
import com.dcd.vhr.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartmentByParentId(Integer parentId) {
        return departmentMapper.getAllDepartmentByParentId(parentId);
    }
}
