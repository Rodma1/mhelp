package com.chen.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.common.lang.Const;
import com.chen.dao.entity.Menu;
import com.chen.dao.entity.RoleMenu;
import com.chen.dao.entity.User;
import com.chen.dao.mapper.MenuMapper;
import com.chen.dao.mapper.RoleMapper;
import com.chen.dao.mapper.RoleMenuMapper;
import com.chen.dao.mapper.UserMapper;
import com.chen.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.service.UserService;
import com.chen.vo.MenuVo;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
//事物出现错误时候就回滚
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private UserService userService;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private MenuMapper menuMapper;
    @Autowired(required = false)
    private RoleMenuMapper roleMenuMapper;
    /**
     * 获取当前用户的菜单栏以及权限
     */
    @Override
    public Result getcurrentUserNav() {
//        通过上下文管理器获取jwt中的用户名
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        通过用户名获取用户数据
        User user=userService.getByUsername(username);
        /**
         * 获取权限数组
         * ：["sys:menu:list","sys:menu:save","sys:user:list"...]
         */
//        将String类型转为数组,通过逗号分隔
        String [] authoritys= StringUtils.tokenizeToStringArray(
                userService.getUserAuthorityInfo(user.getId())
                ,",");
        /**
         * 获取菜单栏
         */
        //获取用户的所有菜单id
        List<Long> menuIds=userMapper.getNavMenuIds(user.getId());
//        获取菜单树
        List<Menu> menus = buildTreeMenu(this.listByIds(menuIds));
//        将Menu数据转为MenuVo数据返回给前端

        return Result.success(
                MapUtil.builder()
                .put("nav",menuVos(menus))
                .put("authoritys",authoritys)
                .map()
        );
    }

    /**
     * 通过id获取菜单
     * @param id
     */
    @Override
    public Result getIDMenu(Long id) {

        return Result.success(menuMapper.selectById(id));
    }

    /**
     * 获取菜单树
     * @return
     */

    @Override
    public Result tree() {
//        获取所有菜单信息,进行排序
        List<Menu> menuList=this.list(new QueryWrapper<Menu>().orderByAsc("orderNum"));

        //转成树状结构
        return Result.success( buildTreeMenu(menuList));
    }

    /**
     * 更新菜单数据
     *
     * @param menu
     */
    @Override
    public Result updateMenu(Menu menu) {
        menu.setUpdated(LocalDateTime.now());
        menuMapper.updateById(menu);
//        清除所有与该菜单相关的权限缓存
        userService.clearUserAuthorityInfoByMenuId(menu.getId());
        return Result.success(menu);
    }

    /**
     * 删除菜单数据
     *
     * @param id
     */
    @Override
    public Result deleteMenu(Long id) {
//        判断是否是父节点，如果是那么就要先删除子节点
        int count=menuMapper.selectCount(new QueryWrapper<Menu>().eq("parent_id",id));
        if (count>0){
            return Result.fail(400,"请先删除子菜单");
        }
//        先清除所有与该菜单相关的权限缓存
        userService.clearUserAuthorityInfoByMenuId(id);
        menuMapper.deleteById(id);
//        同步删除角色表
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("menu_id",id));

        return Result.success("删除成功");
    }

    /**
     * 增加菜单数据
     *
     * @param menu
     */
    @Override
    public Result addMenu(Menu menu) {
        menu.setCreated(LocalDateTime.now());
//        状态为0
        menu.setStatu(Const.STATUS_ON);
//        插入insert
        menuMapper.insert(menu);

        return Result.success("增加成功");
    }

    /**
     * 构造菜单树
     * @param menus
     */
    private List<Menu> buildTreeMenu(List<Menu> menus){
//        定义一个列表
        List<Menu> finalMenus=new ArrayList<>();
//        for循环遍历菜单
        for (Menu menu:menus){
//            循环比较，找每个菜单的节点
            for (Menu e:menus){
//                如果父字段等于menu的id，说明e是menu的子树
                if (e.getParentId()==menu.getId()){
//                    增加进去
                    menu.getChildren().add(e);
                }
            }
//            如果ParentId是0，说明该节点没有父节点，其就是父节点
            if (menu.getParentId()==0L){
                finalMenus.add(menu);
            }

        }
//        最后输出
        return finalMenus;
    }
    private List<MenuVo> menuVos(List<Menu> menus){
        List<MenuVo> menuVoList=new ArrayList<>();
        menus.forEach(menu -> {
            MenuVo menuVo=new MenuVo();
            menuVo.setId(menu.getId());
            menuVo.setTitle(menu.getName());
            menuVo.setComponent(menu.getComponent());
            menuVo.setName(menu.getPerms());
            menuVo.setIcon(menu.getIcon());
            menuVo.setPath(menu.getPath());
            //如果有子菜单
            if (menu.getChildren().size()>0){
//                获取子菜单的信息在放入到父菜单中
                menuVo.setChildren(menuVos(menu.getChildren()));
            }
            menuVoList.add(menuVo);
        });
        return  menuVoList;
    }
}
