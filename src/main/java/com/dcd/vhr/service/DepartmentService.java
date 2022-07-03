package com.dcd.vhr.service;

import com.dcd.vhr.mapper.DepartmentMapper;
import com.dcd.vhr.mapper.EmployeeMapper;
import com.dcd.vhr.mapper.EmployeeecMapper;
import com.dcd.vhr.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    EmployeeMapper employeeMapper;

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

    @Transactional
    public Integer deleteDepartment(Integer id) {
        //1.先检查是否为Parent,是的话无法删除
        Department department = departmentMapper.selectByPrimaryKey(id);
        Integer pid = department.getParentid();
        if (department.getIsparent()){
            return -2;
        }
        //2.查询部门下是否有员工,有则无法删除
        if (employeeMapper.selectHasEmpByDepId(id) > 0){
            return -1;
        }
        //3.不为Parent且无员工,删除部门
        Integer result = departmentMapper.deleteByPrimaryKey(id);
        //4.检查被删除的部门的父部门是否还有子部门，如果没有则修改isParent
        Department parentDep = departmentMapper.selectByPrimaryKey(pid);
        if (departmentMapper.selectHasDepByPid(parentDep.getId()) == 0){
            parentDep.setIsparent(false);
            departmentMapper.updateByPrimaryKeySelective(parentDep);
        }
        return result;
    }
}
