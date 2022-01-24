package com.chen.service;

import com.chen.dao.pojo.SignUser;
import com.chen.dao.pojo.SignUserHistory;
import com.chen.vo.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SignUserService {
    /**
     * 签到接口
     * @return
     */
    @Transactional
    Result signIn();

    /**
     * 获取用户历史签到信息
     */
    Result listSignInHistory();

    /**
     * 用户今日是否签到
     * @return
     */
    Result UserTodaySignIn();
}
