package com.chen.controller;


import com.chen.service.UserService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    /**
     * 获取对应用户id的角色拥有的权限
     */
    @Autowired
    private UserService userService;
    @GetMapping("/authority/{id}")
    public Result getUserAuthorityInfo(@PathVariable("id") Long userId){
        return Result.success(userService.getUserAuthorityInfo(userId));
    }

}
