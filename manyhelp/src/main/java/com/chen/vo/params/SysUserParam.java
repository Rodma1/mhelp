package com.chen.vo.params;

import lombok.Data;

/**
 * 用户的参数
 */

@Data
public class SysUserParam {
    //    头像
    private String avatar;
    //邮箱
    private String email;
    //昵称
    private String nickname;
    //原密码

    private String beforePssword;

    //密码
    private String password;
}
