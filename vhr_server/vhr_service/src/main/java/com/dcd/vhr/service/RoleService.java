package com.dcd.vhr.service;

import com.dcd.vhr.mapper.RoleMapper;
import com.dcd.vhr.model.Menu;
import com.dcd.vhr.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insertSelective(role);
    }

    public Integer deleteRoleByRid(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
