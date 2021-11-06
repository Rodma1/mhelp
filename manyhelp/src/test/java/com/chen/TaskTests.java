package com.chen;

import com.chen.dao.mapper.TaskMapper;
import com.chen.dao.pojo.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskTests {
    @Autowired(required = false)
    private TaskMapper taskMapper;
    @Test
    void contextLoads() {
        List<Task> taskList=taskMapper.selectList(null);
        System.out.println("list"+taskList+"size"+taskList.size());
    }
}
