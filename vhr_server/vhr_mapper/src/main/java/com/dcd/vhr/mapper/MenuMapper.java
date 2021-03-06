package com.dcd.vhr.mapper;

import com.dcd.vhr.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusIdByHrId(Integer id);

    List<Menu> getAllMenusWithRoles();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);
}