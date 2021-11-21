package com.chen.service;

import com.chen.dao.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.vo.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
public interface MenuService extends IService<Menu> {
    /** * 获取当前用户的菜单栏以及权限 */
    Result getcurrentUserNav();
    /**
     * 通过菜单id获取数据
     */
    Result getIDMenu(Long id);
    /**
     * 获取菜单树
     * @return
     */
    Result tree();
    /**
     * 更新菜单数据
     */
    Result updateMenu(Menu menu);
}
