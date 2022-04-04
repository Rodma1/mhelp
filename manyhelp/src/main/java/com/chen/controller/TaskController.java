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
    @Cache(expire = 1 * 60 * 1000,name = "listTask")//2分钟清空缓存
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
     *
     */
    @PostMapping("usertasks")
    public Result userTask(@RequestBody PageParams pageParams){
        return taskService.getUserTask(pageParams);

    }
    /**
     * 查询指定的用户接受的任务信息
     * 用户id
     */
    @PostMapping("uaccepttasks")
    public Result userATask(@RequestBody PageParams pageParams){
        return taskService.getUserATask(pageParams);

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
    /**
     * 更新任务
     */
    @PostMapping("updatetask")
    public Result updateTask(@RequestBody TaskParam taskParam){
        return taskService.updateTask(taskParam);
    }
    /**
     * 删除某个发布了任务的标签
     */
    @GetMapping("deltasktag/{id}")
    public Result delTaskTag(@PathVariable("id") Long id){
        return taskService.delTaskTag(id);
    }
    /**
     * 增加已经发布任务里面的标签
     */
    @PostMapping("insertTaskTag")
    public Result insertTaskTag(@RequestBody TaskParam taskParam){
        return taskService.insertTaskTag(taskParam);
    }
    /**
     * 用户接受任务
     * 传入要接收的任务id
     */
    @PostMapping("accepttask/{id}")
    public Result acceptTask(@PathVariable("id") Long id){
        return  taskService.acceptTask(id);
    }
    /**
     * 修改任务状态
     * 确认任务完成需要获取任务的id
     */
    @PostMapping("altertask")
    public Result altertask(@RequestBody TaskParam taskParam){
        return  taskService.alterTask(taskParam);
    }
}
