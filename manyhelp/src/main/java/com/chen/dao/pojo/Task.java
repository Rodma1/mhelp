package com.chen.dao.pojo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

//创建任务实体类
@Data
public class Task {


    public static final int Task_TOP = 1;

    public static final int Task_Common = 0;
    private Long id;
//    评论数量
    private Integer commentCounts;
//    创建时间
    private Long createDate;
    //    结束时间
    private Long endDate;
//    简介
    private String summary;
//    标题
    private String title;
//    流量数据
    private Integer viewCounts;
//    是否置顶
    private Integer weight ;
    /**
     * 作者id
     */
    @JsonSerialize(using= ToStringSerializer.class)
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
     * 接受任务的用户id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long acceptUserId;
    /**
     * 发布任务的学校id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long publishSchoolId;

    /**
     * 发布学校的名称
     */
    private String publishSchoolName;

    /**
     * 图片
     */
    private String images;
    /**
     * 任务进行状态
     */
    private Integer status;
    /**
     * 金币
     */
    private Integer money;
}
