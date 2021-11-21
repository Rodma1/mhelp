package com.chen.controller;


import com.chen.service.MenuService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        return Result.success(menuService.getById(id));
    }

}
