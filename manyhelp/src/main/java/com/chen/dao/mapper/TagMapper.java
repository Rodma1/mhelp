package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    /*
    根据任务id查询标签列表
     */
    List<Tag> findTagsByTaskId(Long taskId);
//    找到任务中最热火的前几个标签
    List<Long> findHotsTagIds(int limit);
//    通过id（List<Long>）找到标签对应的名字
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
