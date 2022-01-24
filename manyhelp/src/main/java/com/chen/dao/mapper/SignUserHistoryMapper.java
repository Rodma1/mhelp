package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.pojo.SignUserHistory;

import java.util.List;

public interface SignUserHistoryMapper extends BaseMapper<SignUserHistory> {
    List<SignUserHistory> findListByUserId(Long userId);
}
