package com.chen.controller;

import com.chen.service.UserService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public Object test(){
        return userService.list();
    }
}
