package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.dao.dos.Archives;
import com.chen.dao.mapper.TaskBodyMapper;
import com.chen.dao.mapper.TaskMapper;
import com.chen.dao.mapper.TaskTagMapper;
import com.chen.dao.pojo.Task;
import com.chen.dao.pojo.TaskBody;
import com.chen.dao.pojo.TaskTag;
import com.chen.dao.pojo.SysUser;
import com.chen.service.*;
import com.chen.utils.UserThreadLocal;
import com.chen.vo.*;
import com.chen.vo.params.TaskParam;
import com.chen.vo.params.PageParams;
import com.sun.javafx.scene.control.skin.TooltipSkin;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//实现任务服务层的接口

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired(required = false)
    private TaskMapper taskMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TagService tagService;
//任务类别
    @Autowired
    private CategoryService categoryService;
//    任务内容
    @Autowired
    private TaskBodyService taskBodyService;
//    线程池
    @Autowired
    private TreadService threadService;
//    任务标签表映射
    @Autowired(required = false)
    private TaskTagMapper taskTagMapper;
//    任务内容表映射
    @Autowired(required = false)
    private TaskBodyMapper taskBodyMapper;
    //        循环遍历到列表中，这里的copyList使用到了重载
    /*使用mybatis映射文件写得任务列表查询
     */
    @Override
    public Result listTasksPage(PageParams pageParams) {
//        创建页数对象
        Page<Task> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        任务归档返回对应的年月分类和标签
        IPage<Task> taskIPage = this.taskMapper.listTask(
                page,
                null,
                null,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth(),
                pageParams.getStatus());

        List<Task> record=taskIPage.getRecords();
        return Result.success(copyList(record,true,true));
    }


    private  List<TaskVo> copyList(List<Task> records, boolean isTags, boolean isAuthor){
        List<TaskVo> taskVoList =new ArrayList<>();

        for (Task record:records){
            taskVoList.add(copy(record,isTags,isAuthor,false,false));

        }
        return taskVoList;
    }
    private List<TaskVo> copyList(List<Task> records, boolean isTag, boolean isAuthor, boolean isBody) {
        List<TaskVo> taskVoList = new ArrayList<>();
        for (Task record : records) {
            taskVoList.add(copy(record,isTag,isAuthor,isBody,false));
        }
        return taskVoList;
    }
    private List<TaskVo> copyList(List<Task> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<TaskVo> taskVoList = new ArrayList<>();
        for (Task record : records) {
            taskVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return taskVoList;
    }
    private TaskVo copy(Task task, boolean isTags, boolean isAuthor, boolean isBody, boolean isCategory){
//        new对象
        TaskVo taskVo =new TaskVo();
//        转为String类型
        taskVo.setId(String.valueOf(task.getId()));
        BeanUtils.copyProperties(task, taskVo);
        taskVo.setCreateDate(new DateTime(task.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        //并不是所有的接口 都需要标签 ，作者信息
        if (isTags){//如果需要显示标签
//            List<TagVo> tags=tagsService.findTagsByTaskId
            Long taskId= task.getId();
            taskVo.setTags(tagService.findTagsByTaskId(taskId));

        }
        //并不是所有接口都需要用户和标签
        if (isAuthor) {
            SysUser sysUser = sysUserService.findUserById(task.getAuthorId());
            taskVo.setAuthor(sysUser.getNickname());
            taskVo.setAvatar(sysUser.getAvatar());
        }
//        内容
        if (isBody){
            TaskBodyVo taskBody = taskBodyService.findTaskBody(task.getId());
            taskVo.setBody(taskBody);
        }
//        类别
        if (isCategory){
            CategoryVo categoryVo = categoryService.findCategoryById(task.getCategoryId());
            taskVo.setCategory(categoryVo);
        }
        return taskVo;
    }
//    最热任务查询语句实现,返回他的id和标题
    @Override
    public  Result hotTask(int limit){
//        初始化mapper映射,查询Task数据表的信息
        LambdaQueryWrapper<Task> queryWrapper=new LambdaQueryWrapper<>();
//        设置排序，ViewCounts浏览量倒序排
        queryWrapper.orderByDesc(Task::getViewCounts);
//        只查询id和title
        queryWrapper.select(Task::getId, Task::getTitle);
//        最后在加上前limit几个,注意这里的空格
        queryWrapper.last("limit "+limit);
//        执行查询语句，相当于select id,title from task order by view_counts desc limit #{limit}
        List<Task> tasks = taskMapper.selectList(queryWrapper);
        return Result.success(copyList(tasks,false,false));
    }

    //    最新任务查询语句实现,返回他的id和标题,通过比较创建时间
    @Override
    public  Result newTasks(int limit){
//        初始化mapper映射,查询Task数据表的信息
        LambdaQueryWrapper<Task> queryWrapper=new LambdaQueryWrapper<>();
//        设置排序，CreateDate创建时间倒序排
        queryWrapper.orderByDesc(Task::getCreateDate);
//        只查询id和title
        queryWrapper.select(Task::getId, Task::getTitle);
//        最后在加上前limit几个,注意这里的空格
        queryWrapper.last("limit "+limit);
//        执行查询语句，相当于select id,title from task order by create_data desc limit #{limit}
        List<Task> tasks = taskMapper.selectList(queryWrapper);
        return Result.success(copyList(tasks,false,false));
    }
    //    任务归档
    @Override
    public Result listArchives() {
//        获取mapper映射查询到的数据
        List<Archives> archivesList= taskMapper.listArchives();
        return Result.success(archivesList);
    }
    //   点击进入任务内容查看详情
    @Override
    public TaskVo findTaskById(Long id) {
        /**
         * 1. 根据id查询任务信息
         * 2. 根据bodyId和categoryid去做关联查询
         * -- 查询任务内容详情
         * SELECT id,comment_counts,create_date,summary,
         * title,view_counts,weight,author_id,body_id,category_id FROM mh_task WHERE id=#{id}
         */
        Task task = taskMapper.selectById(id);
        //把更新操作扔到线程池中
        threadService.updateViewCount(taskMapper, task);
        return copy(task,true,true,true,true);
    }

    /**
     * 发布任务内容
     * @param taskParam
     * @return
     */
    @Override
    public Result publish(TaskParam taskParam) {
//        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        /**
         * 所需参数及具体实现
         * 1. 发布任务  目的 构建Task对象
         * 2. 作者id 当前的登录用户
         * 3. 标签  要将标签加入到 并联列表当中
         * 4. body 内容存储 task bodyId
         */
//        将所需数据表所需字段都存入到数据表中
        Task task = new Task();
        task.setAuthorId(sysUser.getId());
        Long id=Long.parseLong(taskParam.getCategory().getId());
        task.setCategoryId(id);
        task.setCreateDate(System.currentTimeMillis());
        task.setCommentCounts(0);
        task.setSummary(taskParam.getSummary());
        task.setTitle(taskParam.getTitle());
        task.setViewCounts(0);

        task.setImages(taskParam.getImages());

        task.setPublishSchoolName(taskParam.getPublishSchoolName());

        //默认为Task_Common
        task.setWeight(Task.Task_Common);
//        在任务最后面id加
        task.setBodyId(-1L);
//        执行插入 ,插入之后 会生成一个任务id
        this.taskMapper.insert(task);
        /**
         * 接下来是标签表的插入
         * 如果标签参数不为空，那么就将任务Id和对应的标签Id存入到任务标签表mh_task_tag
         */
        List<TagVo> tags= taskParam.getTags();
        if (tags!=null) {
            for (TagVo tag : tags) {
                TaskTag taskTag = new TaskTag();
                taskTag.setTaskId(task.getId());
                taskTag.setTagId(Long.parseLong(tag.getId()));
//                将获取的数据插入到任务标签表
                this.taskTagMapper.insert(taskTag);
            }
        }
        /**
         * 接下来是任务内容表的插入mh_task_body
         */
        TaskBody taskBody = new TaskBody();
        taskBody.setContent(taskParam.getBody().getContent());
        taskBody.setContentHtml(taskParam.getBody().getContentHtml());
        taskBody.setTaskId(task.getId());
//        插入到任务表
        taskBodyMapper.insert(taskBody);
        /**
         * 更新任务表task里面的body_id字段，因为我们任务内容表已经插入了任务内容，任务表里也要产生对应的id跟他匹配
         */
//        获取id
        task.setBodyId(taskBody.getId());
//        执行更新操作
        taskMapper.updateById(task);
        /**
         * 数据已经插入完毕了，接下来就是将任务id返回给前端了，前端就可以通过id在请求任务路由进行任务展示
         */

        TaskVo taskVo = new TaskVo();
//        这里直接调用TaskVo对象，你也可以单独设置一个只返回id的对象
        taskVo.setId(String.valueOf(task.getId()));
        return Result.success(taskVo);
    }

    /**
     * 获取指定用户发布的任务消息
     * @param
     * @return
     */
    @Override
    public Result getUserTask(PageParams pageParams) {
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        //        创建页数对象
        Page<Task> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        任务归档返回对应的年月分类和标签
        IPage<Task> taskIPage = this.taskMapper.listTask(
                page,
                sysUser.getId(),
                null,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth(),
                pageParams.getStatus());
        List<Task> record=taskIPage.getRecords();
        return Result.success(copyList(record,true,true));
    }
    /**
     * 获取指定用户接受的任务消息
     * @param
     * @return
     */
    @Override
    public Result getUserATask(PageParams pageParams) {
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        //        创建页数对象
        Page<Task> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        任务归档返回对应的年月分类和标签
        IPage<Task> taskIPage = this.taskMapper.listTask(
                page,
                null,
                sysUser.getId(),
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth(),
                pageParams.getStatus());
        List<Task> record=taskIPage.getRecords();
        return Result.success(copyList(record,true,true));
    }

    /**
     * 更新任务
     * @param taskParam
     * @return
     */
    @Override
    public Result updateTask(TaskParam taskParam) {
        /**
         * 所需参数及具体实现
         * 1. 发布任务  目的 构建Task对象
         * 2. 作者id 当前的登录用户
         * 3. 标签  要将标签加入到 并联列表当中
         * 4. body 内容存储 task bodyId
         */
//        将所需数据表所需字段都存入到数据表中
        Task task = new Task();
        task.setId(taskParam.getId());
        Long id=Long.parseLong(taskParam.getCategory().getId());
        task.setCategoryId(id);
        task.setSummary(taskParam.getSummary());
        task.setTitle(taskParam.getTitle());
        task.setImages(taskParam.getImages());
//        执行插入 ,插入之后 会生成一个任务id
        this.taskMapper.updateById(task);
        /**
         * 接下来是任务内容表的插入mh_task_body
         */
        TaskBody taskBody = new TaskBody();
//        获取body对应的id
        taskBody.setId(taskMapper.selectById(task.getId()).getBodyId());
        taskBody.setContent(taskParam.getBody().getContent());
        taskBody.setContentHtml(taskParam.getBody().getContentHtml());
        taskBody.setTaskId(task.getId());
//        插入到任务表
        taskBodyMapper.updateById(taskBody);

        /**
         * 数据已经更新完毕了，接下来就是将任务id返回给前端了，前端就可以通过id在请求任务路由进行任务展示
         */

        TaskVo taskVo = new TaskVo();
//        这里直接调用TaskVo对象，你也可以单独设置一个只返回id的对象
        taskVo.setId(String.valueOf(task.getId()));
        return Result.success(taskVo);

    }
    /**
     * 删除任务里面的标签
     */
    public Result delTaskTag(Long id){
        taskTagMapper.deleteById(id);
        return Result.success("删除成功");
    };
    /**
     * 增加已经发布任务里面的标签
     */
    public Result insertTaskTag(TaskParam taskParam){
        /**
         * 接下来是标签表的插入
         * 如果标签参数不为空，那么就将任务Id和对应的标签Id存入到任务标签表mh_task_tag
         */
        List<TagVo> tags= taskParam.getTags();
        if (tags!=null) {
            for (TagVo tag : tags) {
                TaskTag taskTag = new TaskTag();
                taskTag.setTaskId(taskParam.getId());
                taskTag.setTagId(Long.parseLong(tag.getId()));
//                将获取的数据插入到任务标签表
                this.taskTagMapper.insert(taskTag);
            }
        }
       return Result.success("增加成功");
    };
    /**
     * selectTaskByKeys
     * 查询指定词或者学校id或学校名称的任务
     * @param
     * @return
     */
    @Override
    public Result getTaskByKeys(PageParams pageParams) {
        //        创建页数对象
        Page<Task> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        查询对应的关键词和学校id
        IPage<Task> taskIPage = this.taskMapper.selectTaskByKeys(
                page,
                pageParams.getWords(),
                pageParams.getSchoolid(),
                pageParams.getPublishSchoolName()
        );
        List<Task> record=taskIPage.getRecords();
        return Result.success(copyList(record,true,true));
    }

    /**
     * 寻找未被接受的任务
     * @param pageParams
     * @return
     */
    @Override
    public Result getTaskByKeysNoState(PageParams pageParams) {
        //        创建页数对象
        Page<Task> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
//        查询对应的关键词和学校id
        IPage<Task> taskIPage = this.taskMapper.selectTaskByKeysNoState(
                page,
                pageParams.getWords(),
                pageParams.getSchoolid(),
                pageParams.getPublishSchoolName()
        );
        List<Task> record=taskIPage.getRecords();
        return Result.success(copyList(record,true,true));
    }

    /**
     * 用户接受任务
     * @param taskId
     * @return
     */
    @Override
    public Result acceptTask(Long taskId) {
        /**
         * 接受任务需要登录
         * 检查用户是否登录
         * 获取用户id存入对应的任务表表
         * 修改任务状态status=1，任务在进行中
         */
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        Task task=new Task();
        task.setId(taskId);
        task.setAcceptUserId(sysUser.getId());
        task.setStatus((long)1);
        taskMapper.updateById(task);
        /**
         * 数据已经更新完毕了，接下来就是将任务id返回给前端了，前端就可以通过id在请求任务路由进行任务展示
         */

        TaskVo taskVo = new TaskVo();
//        这里直接调用TaskVo对象，你也可以单独设置一个只返回id的对象
        taskVo.setId(String.valueOf(task.getId()));
        return Result.success(taskVo);
    }

    /**
     * 修改任务状态
     * 修改status状态就可以了
     * @param taskParam
     * @return
     */
    @Override
    public Result alterTask(TaskParam taskParam) {
        /**
         * 用户需要登录
         * 获取用户id,判断是否更发布的用户是一致
         * 修改status=2，表示任务完成
         */
        //        获取用户信息,由于我们使用UserThreadLocal获取信息，所以这个任务输入接口要加入到登录拦截器中，因为你登录了才能有用户信息编辑任务
        SysUser sysUser= UserThreadLocal.get();
        Task task=taskMapper.selectById(taskParam.getId());

        if (!sysUser.getId().equals(task.getAuthorId())){
            return Result.success("你不是发布用户");
        }
//        更新状态
        Task task1=new Task();
        task1.setId(taskParam.getId());
        task1.setStatus(taskParam.getStatus());
        taskMapper.updateById(task1);
        TaskVo taskVo = new TaskVo();
//        这里直接调用TaskVo对象，你也可以单独设置一个只返回id的对象
        taskVo.setId(String.valueOf(task.getId()));
        return Result.success(taskVo);
    }

}
