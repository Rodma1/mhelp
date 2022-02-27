package com.chen.controller;

import com.chen.service.SysUserService;
import com.chen.vo.Result;
import com.chen.vo.params.SysUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户信息
     * @param token
     * @return
     */

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {

//        System.out.println(token);
        return sysUserService.getUserInfoByToken(token);

    }

    /**
     *获取用户信息
     * @return
     */

    @GetMapping("/userInfo")
    public Result userInfo(){
        return sysUserService.getUserInfo();
    }


    /**
     * 上传用户头像
     * @param file
     * @return
     */

    @PostMapping("/upload/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) {
        return sysUserService.uploadavatar(file);
    }

    /**
     * 更新用户信息
     * @param sysUserParam
     * @return
     */

    @PostMapping("/update/userInfo")
    public Result updateUserInfo(@RequestBody  SysUserParam sysUserParam , @RequestHeader("Authorization") String token){

        return sysUserService.updateUser(sysUserParam ,token);

    }


}
