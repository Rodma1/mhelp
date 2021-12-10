package com.chen.service.impl;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.dao.entity.*;
import com.chen.dao.mapper.*;
import com.chen.service.TaskService;
import com.chen.utils.PageUtils;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired(required = false)
    private TaskMapper taskMapper;
    @Autowired(required = false)
    private TaskBodyMapper taskBodyMapper;
    @Autowired(required = false)
    private TagMapper tagMapper;
    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    @Autowired(required = false)
    private TaskTagMapper taskTagMapper;
    @Autowired
    private PageUtils pageUtils;

    /**
     * 任务分页模糊查询
     *
     * @param taskname
     * @return
     */
    @Override
    public Result listTaskPage(String taskname) {
        //        分页模糊查询
//        分页模糊查询
        Page<Task> taskPage = taskMapper.selectPage(pageUtils.getPage(),
                new QueryWrapper<Task>().like(StrUtil.isNotBlank(taskname), "title", taskname));
        taskPage.getRecords().forEach(task -> {
//            查询任务内容
            task.setTaskBody(taskBodyMapper.selectById(task.getBodyId()));
//            查询标签
            task.setCategory(categoryMapper.selectById(task.getCategoryId()));
//            查询标签
            task.setTasktag(tagMapper.findTagsByTaskId(task.getId()));

        });
        return Result.success(taskPage.getRecords());
    }

    /**
     * 批量删除任务
     *
     * @param ids
     */
    @Override
    public Result deleteTask(Long[] ids) {
        //        通过deleteBatchIds可以删除，Arrays.asList()这个方法可以将数组转换为集合
        taskMapper.deleteBatchIds(Arrays.asList(ids));
        //        任务内容表里面的任务也要删除
        taskBodyMapper.delete(new QueryWrapper<TaskBody>().in("task_id", ids));
//        任务标签表里面的内容也要删除
        taskTagMapper.delete(new QueryWrapper<TaskTag>().in("task_id",ids));
        return Result.success("删除成功");
    }


}