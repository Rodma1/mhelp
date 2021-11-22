package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.dao.entity.RoleMenu;
import com.chen.dao.mapper.RoleMenuMapper;
import com.chen.service.RoleMenuService;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Autowired(required = false)
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserService userService;
    /**
     * 给角色分配权限
     *
     * @param roleId
     * @param menuIds
     */
    @Override
    public Result perm(Long roleId, Long[] menuIds) {
//        创建角色菜单列表:存放多条数据
        List<RoleMenu> roleMenus=new ArrayList<>();
//        遍历菜单id
        Arrays.stream(menuIds).forEach(menuId->{
            //        创建角色菜单表
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenus.add(roleMenu);
        });
//        删除原来的这个角色的角色菜单列表信息
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
//        保存新的列表信息
        roleMenus.forEach(roleMenu -> {
            roleMenuMapper.insert(roleMenu);
        });
//        清除所有用户的权限缓存信息
        userService.clearUserAuthorityInfoByRoleId(roleId);
        return  Result.success(menuIds);
    }
}
