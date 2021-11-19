package com.chen;

import com.chen.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Test
    void contextLoads() {
        String token=jwtUtils.generateToken("chen");
//        获取token
        System.out.println(token);
//        解析token
        Claims tokens=jwtUtils.getClaimByToken(token);
        System.out.println(tokens);
    }

}
