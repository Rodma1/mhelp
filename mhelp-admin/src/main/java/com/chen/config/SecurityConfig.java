package com.chen.config;

import com.chen.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginFailureHandler loginFailureHandler;
//    成功配置
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
//    验证码校验
    @Autowired
    private CaptchaFilter captchaFilter;
//  认证失败类
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    //认证过滤器
    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        //认证管理
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        return filter;
    }
//    登出
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
//   密码加密和验证
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    自定义获取用户信息
    @Autowired
    private  UserDetailsServicelImpl userDetailsServicel;

//    白名单,就是访问不会提示登录
    public static final String[] URL_WHITELIST={
            "/webjars/**",
            "/favicon.ico",
            "/captcha",
            "/login",
            "/logout",
            "/test/pass",
            "/user/authority/**",

    };
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
//                登录配置
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                //登出配置
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                // 配置拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()//白名单
                .anyRequest().authenticated()
                //禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//              定义认证失败过滤器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                //在登录前认证一下
                .addFilter(jwtAuthenticationFilter())
//                先加载过滤验证码在登录
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)//登录验证码校验过滤器

        ;
    }
//    UserDetailsServiceImpl配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServicel);
    }
}
