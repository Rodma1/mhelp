package com.chen.controller;


import com.chen.service.RoleService;
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
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 通过角色id获取数据
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('mh:role:list')")
    public Result info(@PathVariable Long id){
        return roleService.getIDRole(id);
    }
    /**
     * 获取角色列表，也可以通过关键字查询
     * @param name
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('mh:role:list')")
    public Result list(String name){
        return roleService.list(name);
    }


}
