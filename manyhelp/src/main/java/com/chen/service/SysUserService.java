package com.chen.service;

import com.chen.dao.pojo.SysUser;
import com.chen.vo.Result;
import com.chen.vo.UserVo;
import com.chen.vo.params.SysUserParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


public interface SysUserService {
//通过用户id找到用户信息
    SysUser findUserById(Long userId);

//    通过用户密码获取信息
    SysUser findUser(String account,String pwd);
//    通过token获取用户信息
    Result getUserInfoByToken(String token);

    /**
     * 获取用户信息
     * @return
     */
    Result getUserInfo();

//    查找用户名是否存在
    SysUser findUserByAccount(String account);
//    保存注册的用户信息
    void save(SysUser sysUser);

//    获取基本的昵称和头像给评论显示
    UserVo findUserVoById(Long id);
//    上传头像
    Result uploadavatar(MultipartFile file);

    /**
     * 更新用户信息
     * @param sysUserParam
     * @return
     */

    @Transactional
    Result updateUser(SysUserParam sysUserParam,String token);

    Result getUserIdInfo(Long id);
}
