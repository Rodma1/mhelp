package com.chen.vo.params;

import lombok.Data;
/**
 * 这里用postman测试的时候注意，开头要小写，要不然无法传递参数
 * "password":"11111",
 * "currentPass":"111"
 */

@Data
public class PassParam {
//    旧密码
    private String currentPass;
//    新密码
    private String password;
}
