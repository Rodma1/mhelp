package com.chen.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.dao.pojo.ChatMsg;


import java.util.List;

public interface ChatMsgMapper extends BaseMapper<ChatMsg> {
//    批量更新消息状态
    public void batchUpdateMsgSigned(List<Long> msgIdList);
}
