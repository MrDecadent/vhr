package com.dcd.vhr.controller.config;

import com.dcd.vhr.model.Menu;
import com.dcd.vhr.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Resource
    private MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenusIdByHrId(){
        return menuService.getMenusIdByHrId();
    }
}
