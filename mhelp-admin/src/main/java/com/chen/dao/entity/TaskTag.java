package com.chen.dao.entity;

import lombok.Data;

/**
 * 获取任务标签表的信息
 */
@Data
public class TaskTag {

    private Long id;
//任务id
    private Long TaskId;
//标签id
    private Long tagId;
}