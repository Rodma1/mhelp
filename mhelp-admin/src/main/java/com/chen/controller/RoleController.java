package com.chen.controller;


import com.chen.dao.entity.Role;
import com.chen.service.RoleService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return roleService.listNameRole(name);
    }
    /**
     * 添加角色
     *
     * @param role
     */
    @PostMapping("/save")
//    授予权限
    @PreAuthorize("hasAuthority('mh:role:save')")
    public  Result save(@Validated @RequestBody Role role){
        return roleService.saveRole(role);
    }
    /**
     * 更新角色
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('mh:role:update')")
    public Result update(@Validated @RequestBody Role role){
        return roleService.updateRole(role);
    }
    /**
     * 删除角色
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('mh:role:delete')")
    public Result delete(@RequestBody Long[] ids){
        return roleService.deleteRole(ids);
    }

}
