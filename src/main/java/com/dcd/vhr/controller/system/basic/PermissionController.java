package com.dcd.vhr.controller.system.basic;

import com.dcd.vhr.model.Role;
import com.dcd.vhr.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Resource
    RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
}
