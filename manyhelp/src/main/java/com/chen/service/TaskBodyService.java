package com.chen.service;


import com.chen.vo.TaskBodyVo;

//获取任务内容接口
public interface TaskBodyService {
//    通过任务表id来获取对应的任务内容
    TaskBodyVo findTaskBody(long taskId);
}
