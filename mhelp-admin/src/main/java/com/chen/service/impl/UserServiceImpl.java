package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.dao.entity.Menu;
import com.chen.dao.entity.Role;
import com.chen.dao.entity.User;
import com.chen.dao.mapper.MenuMapper;
import com.chen.dao.mapper.UserMapper;
import com.chen.service.MenuService;
import com.chen.service.RoleService;
import com.chen.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
