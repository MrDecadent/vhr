package com.dcd.vhr.service;

import com.dcd.vhr.mapper.MenuMapper;
import com.dcd.vhr.mapper.MenuRoleMapper;
import com.dcd.vhr.model.Hr;
import com.dcd.vhr.model.Menu;
import com.dcd.vhr.model.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusIdByHrId(){
        //getPrincipal()当前登录的用户对象
        return menuMapper.getMenusIdByHrId(((Hr) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());
    }

    //返回所有菜单以及所需要的所有角色
    public List<Menu> getAllMenusWithRoles(){
        return menuMapper.getAllMenusWithRoles();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional  //添加事务
    public Integer updateMenuRole(Integer rid, Integer[] mids) {
        //先删除
        menuRoleMapper.deleteByRid(rid);
        //再添加
        return menuRoleMapper.insertRecord(rid,mids);
    }
}
