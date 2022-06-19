package com.dcd.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/sayHello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/salary/sob/hello")
    public String hello2(){
        return "/salary/sob/hello";
    }

    @GetMapping("/salary/sobcfg/hello")
    public String hello3(){
        return "/salary/sobcfg/hello";
    }
}
