package com.chen.service;

import com.chen.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
public interface UserService extends IService<User> {
//    通过用户名查询用户信息
    User getByUsername(String username);
}
