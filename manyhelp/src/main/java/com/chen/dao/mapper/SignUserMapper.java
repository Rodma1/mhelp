package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.pojo.SignUser;

public interface SignUserMapper extends BaseMapper<SignUser> {
    SignUser findByUserId(Long userId);
}
