package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    /*
    根据任务id查询标签列表
     */
    List<Tag> findTagsByTaskId(Long taskId);
}
