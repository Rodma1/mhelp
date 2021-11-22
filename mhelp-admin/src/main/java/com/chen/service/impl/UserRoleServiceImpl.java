package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.dao.entity.User;
import com.chen.dao.entity.UserRole;
import com.chen.dao.mapper.UserMapper;
import com.chen.dao.mapper.UserRoleMapper;
import com.chen.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.service.UserService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    /**
     * 给用户分配角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public Result permRoleUser(Long userId, Long[] roleIds) {
//        定义一个列表
        List<UserRole> userRoles=new ArrayList<>();
//        将数组转换为Stream流的方式;来遍历数组
        Arrays.stream(roleIds).forEach(roleId->{
            UserRole userRole=new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        });
//        删除用户角色表里原来和用户有关的角色
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",userId));
//        保存新的用户权限
        userRoles.forEach(userRole -> {
            userRoleMapper.insert(userRole);
        });
//        清除权限信息
        User user=userMapper.selectById(userId);
        userService.clearUserAuthorityInfo(user.getUsername());
        return Result.success(roleIds);
    }
}
