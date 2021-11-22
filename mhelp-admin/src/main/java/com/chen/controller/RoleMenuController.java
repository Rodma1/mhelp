package com.chen.controller;


import com.chen.service.RoleMenuService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/sys/role-menu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;
    /**
     * 给角色分配权限
     */
    @PostMapping("/perm/{roleId}")
    @PreAuthorize("hasAuthority('mh:role:perm')")
    public Result perm(@PathVariable Long roleId, @RequestBody Long[] menuIds){
        return roleMenuService.perm(roleId,menuIds);
    }
}
