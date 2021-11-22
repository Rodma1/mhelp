package com.chen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mhelp-admin
 * @since 2021-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mh_user")
public class User  extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;
    @TableField(exist = false)
    private List<Role> roleList;


}
