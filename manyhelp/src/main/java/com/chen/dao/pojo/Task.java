package com.chen.dao.pojo;
import lombok.Data;
//创建文章实体类
@Data
public class Task {

    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;
    private Long id;
//    评论数量
    private int commentCounts;
//    创建时间
    private Long createDate;
//    结束时间
    private Long end_date;
//    标题
    private String title;
//    流量数据
    private int viewCounts;
//    是否置顶
    private int weight = Article_Common;
    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;
    /**
     * 任务状态
     */
    private String status;
}