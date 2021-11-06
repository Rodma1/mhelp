package com.chen.dao.pojo;

import lombok.Data;

@Data
public class TaskBody {
    private Long id;
//    内容
    private String content;
//    内容的html
    private String contentHtml;
//    任务id，通过这个id来和对应的任务表关联
    private Long taskId;
}
