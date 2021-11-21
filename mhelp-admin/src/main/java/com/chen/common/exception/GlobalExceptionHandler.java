package com.chen.common.exception;

import com.chen.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	//权限不足
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = AccessDeniedException.class)
	public Result handler(AccessDeniedException e) {
		log.info("security权限不足：----------------{}", e.getMessage());
		return Result.fail(400,"权限不足");
	}

	// 实体校验异常捕获
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Result handler(MethodArgumentNotValidException e) {

		BindingResult result = e.getBindingResult();
		ObjectError objectError = result.getAllErrors().stream().findFirst().get();

		log.error("实体校验异常：----------------{}", objectError.getDefaultMessage());
		return Result.fail(400,objectError.getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public Result handler(IllegalArgumentException e) {
		log.error("Assert异常：----------------{}", e.getMessage());
		return Result.fail(400,e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public Result handler(RuntimeException e) {
		log.error("运行时异常：----------------{}", e.getMessage());
		return Result.fail(400,e.getMessage());
	}

}
