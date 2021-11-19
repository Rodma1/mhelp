package com.chen.controller;

import cn.hutool.core.map.MapUtil;
import com.chen.service.UserService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserService userService;

    //    加密和验证策略
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/test")
    public Object test(){
        return userService.list();
    }
    @GetMapping("/test/pass")
    public Result pmh_sys_logassEncode(){
//        密码加密
        String pass=bCryptPasswordEncoder.encode("111");
//        密码验证
        boolean matches=bCryptPasswordEncoder.matches("111",pass);
        return Result.success(MapUtil.builder()
            .put("pass",pass)
            .put("marches",matches)
            .build()
        );

    }

}
