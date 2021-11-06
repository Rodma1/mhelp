package com.chen.service;

import com.chen.dao.mapper.TaskMapper;
import com.chen.dao.pojo.Task;

public interface TreadService {
//    阅读更新操作需要
    void updateViewCount(TaskMapper taskMapper, Task task);
}
