package com.chen.dao.entity;

import lombok.Data;

@Data
public class Category {

    private Long id;

    private String avatar;
//类别名字
    private String categoryName;
// 描述
    private String description;
}