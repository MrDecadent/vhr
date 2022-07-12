package com.dcd.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/* 
 * Description: 判断当前发出请求的用户是否具备所需要的角色
 * @Date 2022/6/19 15:39
 * @Param 
 */ 

@Component
public class CustomDecisionManager implements AccessDecisionManager {
    @Override
    //authentication登陆成功后的角色信息
    public void decide(Authentication authentication, Object o
            , Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //把请求需要的角色比较和用户有的角色
        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)){//登录就可以访问的角色，判断是否登录
                if (authentication instanceof AnonymousAuthenticationToken){//未登录
                    throw new AccessDeniedException("尚未登陆，请登录！");
                }else {
                    return;
                }
            }
            //获取当前登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
