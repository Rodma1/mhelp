package com.chen.service.impl;

import com.chen.dao.mapper.SchoolMapper;
import com.chen.dao.pojo.School;
import com.chen.service.SchoolService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl  implements SchoolService {

    @Autowired(required = false)
    private SchoolMapper schoolMapper;

    @Override
    public Result selectSchool() {
        List<School> schools=schoolMapper.selectList(null);
        return Result.success(schools);
    }
}
