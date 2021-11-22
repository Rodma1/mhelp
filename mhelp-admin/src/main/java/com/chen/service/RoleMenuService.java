package com.chen.service;

import com.chen.dao.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 给角色分配权限
     */
    Result perm(Long roleId,Long[] menuIds);
}
