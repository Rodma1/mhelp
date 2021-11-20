package com.chen.dao.mapper;

import com.chen.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
public interface UserMapper extends BaseMapper<User> {
//    获取菜单id
    List<Long> getNavMenuIds(Long userId);
}
