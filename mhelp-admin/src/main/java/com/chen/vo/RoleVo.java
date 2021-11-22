package com.chen.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleVo {

    private Long id;

    private LocalDateTime created;
    private LocalDateTime updated;

    private Integer statu;
    private String name;

    private String code;

    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单id
     */
    private List<Long> menuIds;
}
