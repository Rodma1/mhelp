package com.chen.vo;

import com.chen.dao.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVo {
    private Long id;

    private LocalDateTime created;
    private LocalDateTime updated;

    private Integer statu;
    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;
    private List<Role> roleList;
}
