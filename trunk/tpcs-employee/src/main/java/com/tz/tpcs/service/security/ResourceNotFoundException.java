package com.tz.tpcs.service.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用于在代码中，抛出一个自定义的 404资源 未找到异常.
 * 该异常将会被 {@linkplain com.tz.tpcs.web.GlobalExceptionController GlobalExceptionController} 处理
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/5 21:05
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class ResourceNotFoundException extends RuntimeException {
}
