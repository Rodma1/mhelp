package com.chen.security;

import cn.hutool.core.util.StrUtil;
import com.chen.dao.entity.User;
import com.chen.service.UserService;
import com.chen.utils.JwtUtils;
import com.chen.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

/**
 * 认证管理器
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
//    生成jwt工具
    @Autowired
    private  JwtUtils jwtUtils;
//    缓存工具
    @Autowired
    private RedisUtil redisUtil;
//    用户服务
    @Autowired
    private UserService userService;
// 注入用户数据
    @Autowired
    private UserDetailsServicelImpl userDetailsServicel;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
//    自定义一个过滤器用来进行识别jwt
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("jwt 校验 filter");
//        获取请求头里面的jwt
        String jwt=request.getHeader(jwtUtils.getHeader());
//        校验jwt是否为空或者为空格
        if (StrUtil.isBlankOrUndefined(jwt)){
//                 传递到下一个Filter
//            当认证失败的时候会进入AuthenticationEntryPoint
            chain.doFilter(request,response);
            return;
        }
//        解析jwt获取里面的值：用户名
        Claims claim= jwtUtils.getClaimByToken(jwt);
//        如果获取到为空
        if (chain==null){
            throw new JwtException("token异常！");
        }
//        如果获取异常
        if (jwtUtils.isTokenExpired(claim)){
            throw  new JwtException("token已过期");
        }
//        如果解析正常，就获取用户名
        String username=claim.getSubject();
        log.info("用户-{},正在登录！",username);
        /**
         * 获取用户权限信息
         * 1.获取用户id
         * 2. 调用getUserAuthority获取权限
         * 3. 加入到token中
         */
        User user=userService.getByUsername(username);
        List<GrantedAuthority> grantedAuthorities= userDetailsServicel.getUserAuthority(user.getId());
//        ，获取到用户名之后我们直接把封装成UsernamePasswordAuthenticationToken
        log.info("用户-{}-的权限-{}",username,grantedAuthorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                =new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);

        log.info("UsernamePasswordAuthenticationToken:{}",usernamePasswordAuthenticationToken);
        //        交给SecurityContextHolder参数,传递authentication对象
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        传递到下一个Filter

        chain.doFilter(request,response);
    }

}
