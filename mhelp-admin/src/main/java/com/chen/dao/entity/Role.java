package com.chen.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("mh_role")
public class Role  {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    /**
     * 备注
     */
    private String remark;


}
