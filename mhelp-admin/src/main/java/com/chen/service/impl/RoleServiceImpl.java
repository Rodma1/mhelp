package com.chen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.common.lang.Const;
import com.chen.dao.entity.Role;
import com.chen.dao.entity.RoleMenu;
import com.chen.dao.entity.UserRole;
import com.chen.dao.mapper.RoleMapper;
import com.chen.dao.mapper.RoleMenuMapper;
import com.chen.dao.mapper.UserRoleMapper;
import com.chen.service.RoleMenuService;
import com.chen.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.service.UserService;
import com.chen.utils.PageUtils;
import com.chen.vo.Result;
import com.chen.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;
    @Autowired(required = false)
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private PageUtils pageUtils;
//    用户角色映射
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserService userService;
    /**
     * 通过角色id获取数据
     *
     * @param id
     */
    @Override
    public Result getIDRole(Long id) {
//        获取角色数据
        Role role=roleMapper.selectById(id);
//        同时获取他对应的菜单ID
        List<RoleMenu> roleMenus= roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id",id));
        List<Long> menuIds=roleMenus.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());
        RoleVo roleVo=copy(role);
        roleVo.setMenuIds(menuIds);
        return Result.success(roleVo);
    }

    /**
     * 分页模糊查询角色
     * @param name
     */
    @Override
    public Result listNameRole(String name) {
//        翻页模糊查询查询
        Page<Role> rolePage=roleMapper.selectPage(pageUtils.getPage(),
                new QueryWrapper<Role>().like(StrUtil.isNotBlank(name),"name",name));
        return Result.success(rolePage);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public Result saveRole(Role role) {
//        设置时间和转态
        role.setCreated(LocalDateTime.now());
        role.setStatu(Const.STATUS_ON);
        roleMapper.insert(role);
        return Result.success(role);
    }

    /**
     * 更新角色
     *
     * @param role
     */
    @Override
    public Result updateRole(Role role) {
        role.setUpdated(LocalDateTime.now());
        roleMapper.updateById(role);
//        更新缓存
        userService.clearUserAuthorityInfoByRoleId(role.getId());
        return Result.success(role);
    }

    /**
     * 批量删除角色
     *
     * @param ids
     */
    @Override
    public Result deleteRole(Long[] ids) {
//        删除Arrays.asList(ids)数组转集合
        roleMapper.deleteBatchIds(Arrays.asList(ids));
//      同步删除用户角色表和角色菜单表里面的数据
        userRoleMapper.delete(new QueryWrapper<UserRole>().in("role_id",ids));
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().in("role_id",ids));
//       缓存同步删除
        Arrays.stream(ids).forEach(id->{
//            更新缓存
            userService.clearUserAuthorityInfoByRoleId(id);
        });

        return Result.success("删除成功");
    }

    //    如果是列表就转为列表输出
    private List<RoleVo> copyList(List<Role> roles){
        List<RoleVo> roleVoList=new ArrayList<>();
        for (Role role : roles){
            roleVoList.add(copy(role));
        }
        return  roleVoList;
    }
    private RoleVo copy(Role role){
//        new一个RoleVo对象
        RoleVo roleVo=new RoleVo();
//        将role里面的属性赋值到roleVo
        BeanUtils.copyProperties(role,roleVo);
        return roleVo;
    }
}
