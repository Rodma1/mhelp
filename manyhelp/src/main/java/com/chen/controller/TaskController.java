package com.chen.controller;

import com.chen.common.aop.LogAnnotation;
import com.chen.common.cache.Cache;
import com.chen.service.TaskService;
import com.chen.vo.TaskVo;
import com.chen.vo.Result;
import com.chen.vo.params.TaskParam;
import com.chen.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//通过json数据进行交互
@RestController
//设置映射路径（通俗理解就是路由）
@RequestMapping("tasks")
public class TaskController {
    /*
    首页：任务列表
    路由:/tasks
    参数：pageParams页数
    返回值：json数据
    请求：post
     */
    @Autowired
    private TaskService taskService;

    @PostMapping//post请求
//    加上注解，代表要对此接口记录日志，module是模块名称，operation是操作名称
    @LogAnnotation(module = "任务",operation = "获取任务列表")
    @Cache(expire = 5 * 60 * 1000,name = "listTask")
    public Result listTask(@RequestBody PageParams pageParams){
////        TaskVo页面接收数据
//        Result Tasks=taskService.listTasksPage(pageParams);
//        System.out.println(tasks);
//        我们这里要传入数据给Result,数据要从service层里获取
        return taskService.listTasksPage(pageParams);
    }
//    最热任务路由
    @PostMapping("hot")
    public Result hotTask(){
        int limit=5;
        return taskService.hotTask(limit);
    }
//    最新任务

    @PostMapping("new")
    public Result newTask(){
        int limit=5;
        return taskService.newTasks(limit);
    }
//    任务归档
    @PostMapping("listtasks")
    public Result listArchives(){
        return taskService.listArchives();
    }

    /**
     * 任务内容
     */
    @PostMapping("view/{id}")
    @Cache(expire = 5 * 60 * 1000,name = "findTaskById")
    public Result findTaskById(@PathVariable("id") Long id){
        TaskVo taskVo = taskService.findTaskById(id);
        return Result.success(taskVo);
    }
    /**
     * 提交任务接口
     */
    @PostMapping("publish")
    public Result publish(@RequestBody TaskParam taskParam){
//        taskParam数据存入到数据库后在返回
        return taskService.publish(taskParam);
    }
    /**
     * 查询指定的用户发布的任务信息
     * 用户id
     */
    @GetMapping("usertasks/{id}")
    public Result userTask(@PathVariable("id") Long id){
        return taskService.getUserTask(id);

    }
    /**
     * 查询指定的用户接受的任务信息
     * 用户id
     */
    @GetMapping("uaccepttasks/{id}")
    public Result userATask(@PathVariable("id") Long id){
        return taskService.getUserATask(id);

    }
    /**
     * 查找对应词语或者学校的的任务
     */
    @PostMapping("taskbykeys")
    public Result taskbykeys(@RequestBody PageParams pageParams){
        return taskService.getTaskByKeys(pageParams);
    }
    /**
     * 查找对应词语或者学校的为完成的任务
     */
    @PostMapping("taskbynostatus")
    public Result taskbynostatus(@RequestBody PageParams pageParams){
        return taskService.getTaskByKeysNoState(pageParams);
    }


}
