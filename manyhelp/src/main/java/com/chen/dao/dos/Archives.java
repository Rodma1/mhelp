package com.chen.dao.dos;
//任务归档所要获取的时间和任务数量
import lombok.Data;

@Data
public class Archives {
    private Integer year;
    private Integer month;
    private Integer count;
}
