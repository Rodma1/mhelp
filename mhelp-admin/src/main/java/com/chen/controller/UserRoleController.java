package com.chen.controller;


import com.chen.service.UserRoleService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 给用户分配角色
     */
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('mh:user:role')")
    public Result perm(@PathVariable Long userId, @RequestBody Long[] roleIds){
        return userRoleService.permRoleUser(userId,roleIds);
    }
}
