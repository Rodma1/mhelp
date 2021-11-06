package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.pojo.Task;

public interface TaskMapper extends BaseMapper<Task> {
// 这里说一下我们继承leBaseMapper是因为这个类里面已经为我们提供了CRUD方法
}
