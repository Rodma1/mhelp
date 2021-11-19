package com.chen.security;

import cn.hutool.json.JSONUtil;
import com.chen.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义认证失败处理类
 */
@Slf4j
//@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注，标识为一个Bean。
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("认证失败！未登录");
//        设置内容类型
        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        设置状态401
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        获得字节流，通过该字节流的write(byte[] bytes)可以向response缓冲区中写入字节，
//        再由Tomcat服务器将字节内容组成Http响应返回给浏览器。
        ServletOutputStream outputStream=httpServletResponse.getOutputStream();
        Result result = Result.fail(401,"请先登录！");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("utf-8"));
//        刷空输出流,并输出所有被缓存的字节。由于某些流支持缓存功能,该方法将把缓存中所有内容强制输出到流中。
        outputStream.flush();
        outputStream.close();



    }
}
