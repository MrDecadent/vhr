package com.dcd.vhr.controller.system.basic;

import com.dcd.vhr.model.Menu;
import com.dcd.vhr.model.RespBean;
import com.dcd.vhr.model.Role;
import com.dcd.vhr.service.MenuService;
import com.dcd.vhr.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable("rid") Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if (menuService.updateMenuRole(rid,mids) == mids.length){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }
}
