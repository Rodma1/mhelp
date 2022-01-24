package com.chen.controller;

import com.chen.dao.pojo.SignUser;
import com.chen.dao.pojo.SignUserHistory;
import com.chen.service.SignUserService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sign")
public class SignUserController {
    @Autowired
    private SignUserService signUserService;
    /**
     * 用户进行签到
     */
    @PostMapping
     public Result userSignIn(){
        return signUserService.signIn();

     }
    /**
     * 查询用户签到历史消息
     */
    @GetMapping("/listsign")
    public Result selectSignInHistory(String limit){
        return signUserService.listSignInHistory();
    }

    /**
     * 用户今日是否签到
     * @return
     */
    @GetMapping("/todaysign")
    public Result selectTodaySignIn(){
        return signUserService.UserTodaySignIn();
    }
}
