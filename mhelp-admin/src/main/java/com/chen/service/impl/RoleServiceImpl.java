package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.dao.entity.Role;
import com.chen.dao.entity.RoleMenu;
import com.chen.dao.mapper.RoleMapper;
import com.chen.dao.mapper.RoleMenuMapper;
import com.chen.service.RoleMenuService;
import com.chen.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.vo.Result;
import com.chen.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;
    @Autowired(required = false)
    private RoleMenuMapper roleMenuMapper;
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
