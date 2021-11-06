package com.chen.service;

import com.chen.vo.Result;
import com.chen.vo.params.CommentParam;

public interface CommentsService {
//    根据任务id查询评论列表
    Result commentsByTaskId(Long taskId);
//    评论功能实现
    Result comment(CommentParam commentParam);
}
