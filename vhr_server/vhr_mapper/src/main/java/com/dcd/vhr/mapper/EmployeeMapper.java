package com.dcd.vhr.mapper;

import com.dcd.vhr.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Integer selectHasEmpByDepId(Integer depId);

    List<Employee> getEmployeeByPage(@Param("page") Integer page
            , @Param("size") Integer size
            , @Param("keywords") String keywords
            ,@Param("emp") Employee employee
            ,@Param("begindatescope") Date[] begindatescope);

    Long getTotal(@Param("keywords") String keywords
            ,@Param("emp") Employee employee
            ,@Param("begindatescope") Date[] begindatescope);

    Integer getMaxWorkId();
}