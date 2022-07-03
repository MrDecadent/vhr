package com.dcd.vhr.service;

import com.dcd.vhr.mapper.DepartmentMapper;
import com.dcd.vhr.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartmentByParentId(Integer parentId) {
        return departmentMapper.getAllDepartmentByParentId(parentId);
    }

    @Transactional
    public Integer addDepartment(Department dep) {
        //1.添加操作,传入name和pid，同时数据库自动生成id
        dep.setEnabled(true);
        dep.setIsparent(false);
        departmentMapper.insertSelectiveReturnId(dep);
        Integer id = dep.getId();

        //2.更新操作,根据pid查询父部门的depPath,父部门的depPath+id等于自己的depPath
        Department parentDep = departmentMapper.selectByPrimaryKey(dep.getParentid());
        dep.setDeppath(parentDep.getDeppath()+"."+id);
        int result = departmentMapper.updateByPrimaryKeySelective(dep);

        //3.更新操作,查询父部门的isParent是否为false,是则改为true
        if (parentDep.getIsparent()){
            return result;
        }else {
            parentDep.setIsparent(true);
            return departmentMapper.updateByPrimaryKeySelective(parentDep);
        }
    }
}
