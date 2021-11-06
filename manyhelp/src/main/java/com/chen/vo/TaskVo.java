package com.chen.vo;
//设置返回给前端的参数
import lombok.Data;

@Data
public class TaskVo {
//    设置返回给前端需要的参数
    private Long id;
    private String title;
//    评论数量
    private int commentCounts;
    private int viewCounts;
    private int weight;
    /**
     * 创建时间
     */
    private String createDate;
//    任务状态
    private String status;
}