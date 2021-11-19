package com.chen.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.chen.common.lang.Const;
import com.google.code.kaptcha.Producer;
import com.chen.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
//日志
@Slf4j
@RestController
public class AuthController extends BaseController {

	@Autowired
	private Producer producer;
	@GetMapping("/captcha")
	public Result captcha() throws IOException {
//生成32位的随机验证码
		String key = UUID.randomUUID().toString();
//		生成验证码
		String code = producer.createText();
		// 为了测试登录
		key = "aaaaa";
		code = "11111";
//返回64位的一串数据
		BufferedImage image = producer.createImage(code);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", outputStream);
//编码
		BASE64Encoder encoder = new BASE64Encoder();
//		设置前缀
		String str = "data:image/jpeg;base64,";
//编码
		String base64Img = str + encoder.encode(outputStream.toByteArray());
// 存入到redis中
		redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);
		log.info("验证码 -- {} - {}", key, code);
		return Result.success(
				MapUtil.builder()
						.put("token", key)
						.put("captchaImg", base64Img)
						.build()

		);
	}


}
