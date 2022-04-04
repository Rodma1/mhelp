package com.chen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chen.dao.mapper.SysUserMapper;
import com.chen.dao.pojo.SysUser;
import com.chen.service.SysUserService;
import com.chen.utils.GetTokenUrl;
import com.chen.utils.JWTUtils;
import com.chen.utils.UploadImage;
import com.chen.utils.UserThreadLocal;
import com.chen.vo.ErrorCode;
import com.chen.vo.LoginUserVo;
import com.chen.vo.Result;
import com.chen.vo.UserVo;
import com.chen.vo.params.SysUserParam;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    @Autowired(required = false)
    private SysUserMapper sysUserMapper;
    @Autowired
    //    springboot和redis整合
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private GetTokenUrl getTokenUrl;
    @Autowired
    private UploadImage uploadImage;
    @Override
    public SysUser findUserById(Long userid) {
        SysUser sysUser=sysUserMapper.selectById(userid);
//        如果没有用户名就默认
        if(sysUser==null){
            sysUser=new SysUser();
            sysUser.setNickname("昵称");
        }
        return sysUser;
    }
    //    通过用户名和密码配对用户信息返回
    @Override
    public SysUser findUser(String account, String pwd) {
//        设置mapper映射
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
//        比较配对
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,pwd);
//        要查询的的字段
        queryWrapper.select(SysUser::getId,SysUser::getAccount,
                SysUser::getAvatar,SysUser::getNickname,SysUser::getEmail,SysUser::getPassword,SysUser::getSchool);
//        只查询一行
        queryWrapper.last("limit 1");
//        执行select语句
        SysUser sysUser=sysUserMapper.selectOne(queryWrapper);
//        返回查询到的语句
        return sysUser;
    }
// 通过redis的token获取用户信息
    @Override
    public Result getUserInfoByToken(String token) {
//        解析token
        Map<String,Object> map= JWTUtils.checkToken(token);
//        如果参数为null，token过期，未登录，就返回错误
        if (map == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
//        获取redis里面的token
        String userJson =  redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
//      解析userJson获取SysUser类型的属性
        SysUser sysUser= JSON.parseObject(userJson,SysUser.class);

//        创建LoginUseVo对象，用户返回前端的数据
        LoginUserVo loginUserVo=new LoginUserVo();

        //        用户名
        loginUserVo.setAccount(sysUser.getAccount());
//        头像
        loginUserVo.setAvatar(sysUser.getAvatar());
//        用户id
        loginUserVo.setId(String.valueOf(sysUser.getId()));
//        昵称
        loginUserVo.setNickname(sysUser.getNickname());

//        邮箱
        loginUserVo.setEmail(sysUser.getEmail());
        System.out.println(sysUser.getEmail());
        return Result.success(loginUserVo);

    }

    @Override
    public Result getUserInfo() {
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();

        //        创建LoginUseVo对象，用户返回前端的数据
        LoginUserVo loginUserVo=new LoginUserVo();
//        用户名
        loginUserVo.setAccount(sysUser.getAccount());
//        头像
        loginUserVo.setAvatar(sysUser.getAvatar());
//        用户id
        loginUserVo.setId(String.valueOf(sysUser.getId()));
//        昵称
        loginUserVo.setNickname(sysUser.getNickname());

//        邮箱
        loginUserVo.setEmail(sysUser.getEmail());
//        学校
        loginUserVo.setSchool(sysUser.getSchool());
        return Result.success(loginUserVo);

    }


    /*
    查找用户是否存在
     */
    @Override
    public SysUser findUserByAccount(String account) {
//        初始化映射mapper语句
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
//        获取数据库用户数据和account作比较:account=#{account}
        queryWrapper.eq(SysUser:: getAccount,account);
//        找到一条符合的就行
        queryWrapper.last("limit 1");
//     相当于sql:select * from mh_sys_user where account=#{account}
        return sysUserMapper.selectOne(queryWrapper);
    }
    /*
    直接将注册的用户信息插入就可以了
     */
    @Override
    public void save(SysUser sysUser) {
        //注意 默认生成的id 是分布式id 采用了雪花算法
        this.sysUserMapper.insert(sysUser);
    }

    /**
     * 获取返回给前端评论的用户信息
     * @param id 任务id
     * @return
     */
    @Override
    public UserVo findUserVoById(Long id) {

//        查询任务id对应的信息:select * from mh_task where id=#{id};
        SysUser sysUser=sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("神的孩子都在歌唱");
        }
//        获取查询到消息这这三个数据返回
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }

    @Override
    public Result uploadavatar(MultipartFile file) {
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        String avatarUrl=uploadImage.upload(file);
        if (avatarUrl!=null){
            UpdateWrapper updateWrapper=new UpdateWrapper();
            updateWrapper.eq("account",sysUser.getAccount());
            updateWrapper.set("avatar",avatarUrl);
            sysUserMapper.update(null,updateWrapper);
            return Result.success(avatarUrl);
        }else{
            return Result.fail(400,"发生异常，服务器或者图片格式不对");
        }
    }

    /**
     * 更新用户信息
     * @param sysUserParam
     * @return
     */
    @Override
    public Result updateUser(SysUserParam sysUserParam, String token) {
        String slat ="adfg!@#";

        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();

        sysUser.setAvatar(sysUserParam.getAvatar());
        sysUser.setEmail(sysUserParam.getEmail());
        sysUser.setNickname(sysUserParam.getNickname());
        sysUser.setSchool(sysUserParam.getSchool());
        //如果都为false就是没有空
        if (!(StringUtils.isBlank(sysUserParam.getBeforePssword())||StringUtils.isBlank(sysUserParam.getPassword()))){
            //         slat加密盐给死了，也可以自己在数据库设计
            String pwd= DigestUtils.md5Hex(sysUserParam.getBeforePssword()+slat);
//            如果原密码相等，说明可以修改
            if (!pwd.equals(sysUser.getPassword())){
                return Result.success("原密码不对");
            }
            sysUser.setPassword(DigestUtils.md5Hex(sysUserParam.getPassword()+slat));

        }

        sysUserMapper.updateById(sysUser);
        //        删除缓存中的token
        redisTemplate.delete("TOKEN_"+token);
        return Result.success("更新成功，请重新登录");
    }


}
