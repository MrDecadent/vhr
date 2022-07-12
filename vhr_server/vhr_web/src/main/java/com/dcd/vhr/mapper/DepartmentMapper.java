package com.dcd.vhr.mapper;

import com.dcd.vhr.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartmentByParentId(Integer parentId);

    Integer insertSelectiveReturnId(Department dep);

    Integer selectHasDepByPid(Integer parentId);
}