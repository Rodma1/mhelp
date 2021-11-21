package com.chen.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回到前端的菜单数据
 */
@Data
public class MenuVo {
    private Long id;
//    菜单名
    private String title;
//   图标
    private String icon;
//    管理的接口路径
    private String path;
//    授权
    private String name;
//    对应的数据表路径
    private String component;
//    子菜单
    List<MenuVo> children=new ArrayList<>();


}
