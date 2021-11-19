package com.chen.security;

import cn.hutool.json.JSONUtil;
import com.chen.utils.JwtUtils;
import com.chen.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//注入服务:用于标注控制层，相当于struts中的action层
@Component

public class LoginSuccessHandler implements AuthenticationSuccessHandler  {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream= response.getOutputStream();
//        生成jwt返回,通过认证获取名字
        String jwt=jwtUtils.generateToken(authentication.getName());//利用用户名生成jwt
//        把jwt作为请求头返回回去,名称就叫Authorization
        response.setHeader(jwtUtils.getHeader(),jwt);

//        设置返回的结果
        Result result=Result.success("");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

    }
}
