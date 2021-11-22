package com.chen.controller;


import com.chen.dao.entity.User;
import com.chen.service.UserService;
import com.chen.vo.Result;
import com.chen.vo.params.PassParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    /**
     * 通过用户id获取数据
     */
    @GetMapping("/info/{id}")
//    设置权限
    @PreAuthorize("hasAuthority('mh:user:list')")
    public Result info(@PathVariable Long id){
        return userService.getIDUser(id);
    }
    /**
     * 用户自己修改密码
     */
    @PostMapping("/updatePass")
    public Result updatapassuser(@Validated @RequestBody PassParam passParam){
        return userService.updatePassUser(passParam);
    }
    /**
     * 超级管理员重置密码
     */
    @PostMapping("/repass")
//    设置权限
    @PreAuthorize("hasAuthority('mh:user:repass')")
    public Result repass(@RequestBody Long userId){
        return  userService.repass(userId);
    }
    /**
     * 用户分页模糊查询
     */

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('mh:user:list')")
    public  Result page(String username){
        return userService.pageUser(username);
    }
    /**
     * 增加用户
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('mh:user:save')")
    public Result save(@Validated @RequestBody User user){
        return userService.insertUser(user);
    }

}
