package com.chen.controller;

import com.chen.service.TaskService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public Result listTask(String taskname){
        return taskService.listTaskPage(taskname);
    }
    /**
     * 删除任务
     */
    @Transactional
    @PostMapping("/delete")
    public Result deleteTask(@RequestBody Long[] ids){
        return taskService.deleteTask(ids);
    }
}
