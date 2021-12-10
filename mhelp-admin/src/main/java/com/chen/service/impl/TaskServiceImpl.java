package com.chen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.dao.entity.Task;
import com.chen.dao.entity.User;
import com.chen.dao.mapper.TaskMapper;
import com.chen.service.TaskService;
import com.chen.utils.PageUtils;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired(required = false)
    private TaskMapper taskMapper;
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

        return Result.success(taskPage.getRecords());
    }
}