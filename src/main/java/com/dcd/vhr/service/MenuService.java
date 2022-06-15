package com.dcd.vhr.service;

import com.dcd.vhr.mapper.MenuMapper;
import com.dcd.vhr.model.Hr;
import com.dcd.vhr.model.Menu;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> getMenusIdByHrId(){
        //getPrincipal()当前登录的用户对象
        return menuMapper.getMenusIdByHrId(((Hr) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getId());
    }

}
