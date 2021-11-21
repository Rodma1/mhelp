package com.chen.controller;


import com.chen.dao.entity.Menu;
import com.chen.service.MenuService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 获取当前用户的菜单栏以及权限
     */
    @GetMapping("/nav")
    public Result nav(){
        return menuService.getcurrentUserNav();
    }
    /**
     * 通过菜单id获取数据
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('mh:menu:list')")
    public Result info(@PathVariable("id") Long id){
        return menuService.getIDMenu(id);
    }
    /**
     * 获取菜单树
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('mh:menu:list')")
    public Result list(){
        return  menuService.tree();
    }
    /**
     * 更新菜单表
     */
    @PostMapping("/update")
//    更新权限
    @PreAuthorize("hasAuthority('mh:menu:update')")
//    @validated来校验数据,如果数据异常则会统一抛出异常
    public Result update(@Validated @RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }
    /**
     * 删除菜单
     */
//事物出现错误的时候就回滚
    @Transactional
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('mh:menu:delete')")
    public Result delete(@PathVariable Long id){
        return menuService.deleteMenu(id);
    }
}
