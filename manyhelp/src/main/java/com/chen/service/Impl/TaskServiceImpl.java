package com.chen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.dao.mapper.TaskMapper;
import com.chen.dao.pojo.Task;
import com.chen.service.TaskService;
import com.chen.vo.TaskVo;
import com.chen.vo.Result;
import com.chen.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//实现文章服务层的接口
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired(required = false)
    private TaskMapper articleMapper;

    @Override
    public Result listArticlesPage(PageParams pageParams) {
        /*
        分页查询数据库
        baomidou已经自带了分页接口
         */
//        创建页数对象
        Page<Task> page=new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        查询条件
        LambdaQueryWrapper<Task> queryWrapper=new LambdaQueryWrapper<>();
//        是否进行排序
        queryWrapper.orderByDesc(Task::getWeight,Task::getCreateDate);
        Page<Task> articlePage=articleMapper.selectPage(page,queryWrapper);
        List<Task> records=articlePage.getRecords();
//        能直接返回么，明显不能
        List<TaskVo> articleVoList=copyList(records);
        return Result.success(articleVoList);
    }
    //        循环遍历到列表中
    private  List<TaskVo> copyList(List<Task> records){
        List<TaskVo> articleVoList=new ArrayList<>();

        for (Task record:records){
            articleVoList.add(copy(record));

        }
        return  articleVoList;
    }
    private TaskVo copy(Task article){
//        new对象
        TaskVo articleVo=new TaskVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        return articleVo;
    }
}
