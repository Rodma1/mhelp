package com.chen.service;

import com.chen.vo.Result;
import com.chen.vo.TagVo;

import java.util.List;

public interface TagService {
//    通过任务id找到对应的标签信息
    List<TagVo> findTagsByTaskId(Long taskId);

//    获取前limit最热标签
    List<TagVo> hot(int limit);

//    获取所有标签
    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
