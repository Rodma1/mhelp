package com.chen.controller;
import com.chen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

	@Autowired(required = false)
	HttpServletRequest req;

	@Autowired
	RedisUtil redisUtil;



}
