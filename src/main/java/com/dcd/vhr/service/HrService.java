package com.dcd.vhr.service;

import com.dcd.vhr.mapper.HrMapper;
import com.dcd.vhr.model.Hr;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HrService implements UserDetailsService {

    @Resource
    HrMapper hrMapper;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null){
            throw new UsernameNotFoundException("该用户名不存在");
        }
        return hr;
    }

}
