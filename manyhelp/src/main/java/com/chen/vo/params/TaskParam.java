package com.chen.vo.params;

import com.chen.vo.CategoryVo;
import com.chen.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * 任务参数
 */
@Data
public class TaskParam {
    private Long id;
//    任务内容
    private TaskBodyParam body;
//    任务类别
    private CategoryVo category;
//  任务概述
    private String summary;
//    任务标签
    private List<TagVo> tags;
//    标题
    private String title;

}
