package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.dao.mapper.TaskBodyMapper;
import com.chen.dao.pojo.TaskBody;
import com.chen.service.TaskBodyService;
import com.chen.vo.TaskBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskBodyServiceIml implements TaskBodyService {
    @Autowired(required = false)
    private TaskBodyMapper taskBodyMapper;
    @Override
    public TaskBodyVo findTaskBody(long taskId) {
        LambdaQueryWrapper<TaskBody> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskBody::getTaskId,taskId);
        TaskBody taskBody = taskBodyMapper.selectOne(queryWrapper);
        TaskBodyVo taskBodyVo = new TaskBodyVo();
        taskBodyVo.setContent(taskBody.getContent());
//        返回对应的任务内容
        return taskBodyVo;
    }
}
