package com.dcd.vhr.config;

import com.dcd.vhr.model.Menu;
import com.dcd.vhr.model.Role;
import com.dcd.vhr.service.MenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Description: 根据用户传来的请求地址，分析出请求需要什么角色才可以访问
 * @Date 2022/6/19 14:57
 * @Param
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Resource
    MenuService menuService;

    AntPathMatcher AntPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //请求的地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        List<Menu> menus = menuService.getAllMenusWithRoles();
        for (Menu menu : menus) {
            //将请求的Url和数据库中的Url匹配
            if (AntPathMatcher.match(menu.getUrl(),requestUrl)){
                List<Role> roles = menu.getRoles();
                //将所有符合请求Url的菜单的权限的角色的角色名放入数组
                String[] str = new String[roles.size()];
                for (int i = 0; i < str.length; i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
