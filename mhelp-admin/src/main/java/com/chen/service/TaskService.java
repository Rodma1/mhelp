package com.chen.service;

import com.chen.vo.Result;

public interface TaskService {
    /**
     * 任务分页模糊查询
     * @return
     */
    Result listTaskPage(String taskname);
    /**
     * 批量删除任务
     */
    Result deleteTask(Long[] ids);
}
