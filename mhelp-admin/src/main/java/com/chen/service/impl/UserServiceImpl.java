package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.dao.entity.User;
import com.chen.dao.mapper.UserMapper;
import com.chen.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//通过用户名查询用户信息返回

    @Override
    public User getByUsername(String username) {

        return getOne(new QueryWrapper<User>().eq("username",username));
    }
}
