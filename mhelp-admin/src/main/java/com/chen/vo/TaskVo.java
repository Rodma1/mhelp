package com.chen.vo;

import lombok.Data;

@Data
public class TaskVo {
    //    设置返回给前端需要的参数
    private Long id;
    private String title;
    private String summary;//简介
    //    评论数量
    private int commentCounts;
    private int viewCounts;
    private int weight;
    /**
     * 创建时间
     */
    private String createDate;
}
