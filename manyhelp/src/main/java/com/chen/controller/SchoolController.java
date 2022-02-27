package com.chen.controller;

import com.chen.service.SchoolService;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 查询所有学校名称
     * @return
     */
    @GetMapping
    private Result ListSchool(){

        return schoolService.selectSchool();

    }

}
