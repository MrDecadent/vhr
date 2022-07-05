package com.dcd.vhr.service;

import com.dcd.vhr.mapper.HrMapper;
import com.dcd.vhr.mapper.HrRoleMapper;
import com.dcd.vhr.model.Hr;
import com.dcd.vhr.utils.HrUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Resource
    HrMapper hrMapper;

    @Resource
    HrRoleMapper hrRoleMapper;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null){
            throw new UsernameNotFoundException("该用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs(String keywords) {
        //不查自己的id
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public boolean updateHrRoles(Integer hid, Integer[] rids) {
        //先删除，再添加
        hrRoleMapper.deleteByHrid(hid);
        return hrRoleMapper.addRole(hid,rids) == rids.length;
    }

    @Transactional
    public Integer deleteHrById(Integer id) {
        hrRoleMapper.deleteByHrid(id);
        return hrMapper.deleteByPrimaryKey(id);
    }
}
