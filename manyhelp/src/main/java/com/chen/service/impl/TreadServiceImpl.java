package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.dao.mapper.TaskMapper;
import com.chen.dao.pojo.Task;
import com.chen.service.TreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 更新任务阅读量
 */
@Service
@Component
public class TreadServiceImpl implements TreadService {
//期望此操作在线程池执行，不会影响原有的主线程
    @Override
    @Async("taskExecutor")
    public void updateViewCount(TaskMapper taskMapper, Task task) {
//        获取阅读量
        int getViewcount= task.getViewCounts();
//        new一个任务对象
        Task taskUpdate =new Task();
//        阅读量加一
        taskUpdate.setViewCounts(getViewcount+1);
//        初始化mapper映射
        LambdaQueryWrapper<Task> queryWrapper=new LambdaQueryWrapper<>();
//        对比id是否对应
        queryWrapper.eq(Task::getId, task.getId());
        //设置一个 为了在多线程环境下 线程安全
        queryWrapper.eq(Task::getViewCounts, task.getViewCounts());
        //update task set view_count=100 where view_count=99 and id=11
        taskMapper.update(taskUpdate,queryWrapper);
        try {
            //睡眠5秒 证明不会影响主线程的使用
            Thread.sleep(5000);
            System.out.println("更新完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
