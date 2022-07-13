package com.dcd.vhr.service.utils;

import com.dcd.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
    public static Hr getCurrentHr(){
        //返回当前用户对象
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
