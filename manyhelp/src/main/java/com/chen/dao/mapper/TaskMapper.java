package com.chen.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.dao.dos.Archives;
import com.chen.dao.pojo.Task;
import com.chen.vo.params.TaskParam;


import java.util.List;

//BaseMapper是mybatis-plus提供的，可以很快查询到task，然后可以很快的和数据库表自动关联
public interface TaskMapper extends BaseMapper<Task>{
//    这里说一下我们继承leBaseMapper是因为这个类里面已经为我们提供了CRUD方法
//    获取任务的同一年月发布的数量
    List<Archives> listArchives();
//任务列表and任务归档功能
    IPage<Task> listTask(Page<Task> page,Long authorId,Long acceptUserId,Long categoryId, Long tagId, String year, String month,Long status);

    /**
     * selectTaskByKeys
     * @param
     * @return
     */
    IPage<Task> selectTaskByKeys(Page<Task> page, String words,Long schoolid);

    /**
     * selectTaskByKeysNoState
     * @param
     * @return
     */
    IPage<Task> selectTaskByKeysNoState(Page<Task> page,String words,Long schoolid);


}
