package com.chen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.common.lang.Const;
import com.chen.dao.entity.Menu;
import com.chen.dao.entity.Role;
import com.chen.dao.entity.User;
import com.chen.dao.entity.UserRole;
import com.chen.dao.mapper.MenuMapper;
import com.chen.dao.mapper.UserMapper;
import com.chen.dao.mapper.UserRoleMapper;
import com.chen.service.MenuService;
import com.chen.service.RoleService;
import com.chen.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.utils.PageUtils;
import com.chen.utils.RedisUtil;
import com.chen.vo.Result;
import com.chen.vo.RoleVo;
import com.chen.vo.UserVo;
import com.chen.vo.params.PassParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//    缓存
    @Autowired
    private RedisUtil redisUtil;
//    角色服务注入
    @Autowired
    private RoleService roleService;
//    注入用户数据库映射
    @Autowired(required = false)
    private UserMapper userMapper;
//    注入菜单服务
    @Autowired
    private MenuService menuService;
//    注入菜单mapper
    private MenuMapper menuMapper;
//    密码校验
    @Autowired
    private PasswordEncoder passwordEncoder;
//    分页
    @Autowired
    private PageUtils pageUtils;
//    用户角色表映射
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;
    //通过用户名查询用户信息返回
    @Override
    public User getByUsername(String username) {

        return getOne(new QueryWrapper<User>().eq("username",username));
    }

    /**
     * 获取用户的角色和权限信息
     * @param userId
     * @return
     */
    @Override
    public String getUserAuthorityInfo(Long userId) {
//        获取用户信息
        User user=this.getById(userId);
//        初始化权限
        String authority=null;
//        如果缓存中有，就先从缓存中取
        if (redisUtil.hasKey("GrantedAuthority:"+user.getUsername())){
        //如果存在就从缓存中获取
            authority=(String) redisUtil.get("GrantedAuthority:"+user.getUsername());
        }
        //如果缓存中没有那么我们就从数据库获取
        else{
            //获取对应的角色信息:
            //select * from mh_role where id in (select role_id from mh_user_role where user_id=userId)
            List<Role> roles= roleService.list(new QueryWrapper<Role>()
            .inSql("id","select role_id from mh_user_role where user_id="+userId));
            //获取对应的菜单id
            List<Long> menuIds=userMapper.getNavMenuIds(userId);
            //通过上面的菜单id获取菜单信息
            List<Menu> menus=menuService.listByIds(menuIds);
            //在每个角色前加上前缀ROLE_:ROLE_admin，通过逗号分隔，变成String字符串类型
            String roleNames=roles.stream().map(r->"ROLE_"+r.getCode()).collect(Collectors.joining(","));
            //同理上面，将菜单中的权限perm变成字符串
            String permNames=menus.stream().map(m-> m.getPerms()).collect(Collectors.joining(","));
            //在将角色和权限合并起来，就是我们需要返回的权限
            authority=roleNames.concat(",").concat(permNames);
            //"ROLE_normal,mh:manage,mh:user:list,mh:role:list,mh:menu:list,mh:tools,mh:dict:list"
            log.info("用户ID - {} ---拥有的权限：{}", userId, authority);
            //在存入redis中
            redisUtil.set("GrantedAuthority:"+user.getUsername(),authority);
        }
        //返回获取到的权限
        return authority;
    }

    /**
     * 删除某个用户的权限信息
     * @param username
     */
    @Override
    public void clearUserAuthorityInfo(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    /**
     * 删除所有与该菜单关联的所有用户的权限信息
     * @param menuId
     */
    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
//        查找与菜单id关联的所有用户角色
        List<User> users=userMapper.listByMenuId(menuId);
//        通过for循环逐一删除
        users.forEach(user -> {
            this.clearUserAuthorityInfo(user.getUsername());
        });
    }

    /**
     * 删除所有与该角色关联的所有缓存信息
     * @param roleId
     */
    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {
//        执行sql获取关联的用户信息
        List<User> users=this.list(new QueryWrapper<User>()
                .inSql("id",
                        "select user_id from mh_user_role where role_id = "
                                + roleId));
//        遍历清除
        users.forEach(user -> {
            this.clearUserAuthorityInfo(user.getUsername());
        });
    }

    /**
     * 通过用户id获取数据
     *
     * @param id
     */
    @Override
    public Result getIDUser(Long id) {
//        获取用户信息
        User user=userMapper.selectById(id);
//        判断是否存在
        Assert.notNull(user,"找不到该管理员");
        UserVo userVo=copy(user);
//        通过用户id获取用户权限信息
        List<Role> roles=userMapper.listRolesByUserId(id);
        userVo.setRoleList(roles);
        return Result.success(userVo);
    }

    /**
     * 用户自己修改密码
     *
     * @param passParam
     */
    @Override
    public Result updatePassUser(PassParam passParam) {
        QueryWrapper queryWrapper=new QueryWrapper();
        //        通过上下文管理器获取jwt中的用户名
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        通过用户名获取用户信息
        User user=this.userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
//       判断旧密码是否正确
        boolean matches=passwordEncoder.matches(passParam.getCurrentPass(),user.getPassword());
        if (!matches){
            return Result.fail(400,"旧密码不正确！");
        }
//        密码加密存入
        user.setPassword(passwordEncoder.encode(passParam.getPassword()));
        user.setUpdated(LocalDateTime.now());
//        更新
        userMapper.updateById(user);
//        清除缓存密码，让用户再次登录
        this.clearUserAuthorityInfo(username);
        return Result.success("更新密码成功,请重新登录");
    }

    /**
     * 超级管理员重置密码
     *
     * @param userId
     */
    @Override
    public Result repass(Long userId) {
//        获取要重置的用户信息
        User user=userMapper.selectById(userId);
//        重置密码
        user.setPassword(passwordEncoder.encode(Const.DEFAULT_PASSWORD));
//        更新时间
        user.setUpdated(LocalDateTime.now());
        userMapper.updateById(user);
//        清空缓存
        this.clearUserAuthorityInfo(user.getUsername());
        return Result.success(copy(user));
    }

    /**
     * 用户分页模糊查询
     *
     * @param username
     */
    @Override
    public Result pageUser(String username) {
//        分页模糊查询
        Page<User> userPage=userMapper.selectPage(pageUtils.getPage(),
                new QueryWrapper<User>().like(StrUtil.isNotBlank(username),"username",username));
//        获取每个用户对应的角色权限
        userPage.getRecords().forEach(user -> {
            user.setRoleList(userMapper.listRolesByUserId(user.getId()));
        });

        return Result.success(userPage);
    }

    /**
     * 创建用户
     *
     * @param user
     */
    @Override
    public Result insertUser(User user) {
//        设置时间和状态
        user.setCreated(LocalDateTime.now());
        user.setStatu(Const.STATUS_ON);
//        初始默认密码
        user.setPassword(Const.DEFAULT_PASSWORD);
//        如果检测到密码微刊就返回
        if (StrUtil.isBlank(user.getPassword())){
            return Result.fail(400,"密码不能为空");
        }
//        密码加密
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
//        默认头像
        user.setAvatar(Const.DEFULT_AVATAR);
//        更新
        userMapper.insert(user);
        return Result.success(copy(user));
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public Result updateUser(User user) {
//        设置更新时间
        user.setUpdated(LocalDateTime.now());
//        如果密码不为空
        if (StrUtil.isNotBlank(user.getPassword())){
            String password=passwordEncoder.encode(user.getPassword());
//            设置密码
            user.setPassword(password);
        }
//        更新操作
        userMapper.updateById(user);
        return Result.success(copy(user));
    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public Result deleteUser(Long[] ids) {
//        通过deleteBatchIds可以删除，Arrays.asList()这个方法可以将数组转换为集合
        userMapper.deleteBatchIds(Arrays.asList(ids));
//        用户角色表里面的用户也要删除
        userRoleMapper.delete(new QueryWrapper<UserRole>().in("user_id",ids));

        return Result.success("删除成功");
    }

    //    如果是列表就转为列表输出
    private List<UserVo> copyList(List<User> users){
        List<UserVo> userVoList=new ArrayList<>();
        for (User user : users){
            userVoList.add(copy(user));
        }
        return  userVoList;
    }
    private UserVo copy(User user){
//        new一个RoleVo对象
        UserVo userVo=new UserVo();
//        将role里面的属性赋值到roleVo
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

}
