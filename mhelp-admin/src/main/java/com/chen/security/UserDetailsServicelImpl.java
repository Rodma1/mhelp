package com.chen.security;

import com.chen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.chen.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

//想要重新获取数据，就要重写UserDetailsService接口
@Slf4j
@Service
public class UserDetailsServicelImpl implements UserDetailsService {
//    注入用户服务接口
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        通过用户名获取用户数据
        User user=userService.getByUsername(username);
//        如果user为空说明没有这个用户
        if (user==null){
            throw new UsernameNotFoundException("用户名和密码不正确");
        }
//        调用自定义的AccountUser，返回值
        return new AccountUser(user.getId(),user.getUsername(),user.getPassword(),new TreeSet<>());

    }
}
