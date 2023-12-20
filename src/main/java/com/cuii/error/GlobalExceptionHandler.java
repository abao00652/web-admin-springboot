package com.cuii.error;

import cn.dev33.satoken.error.SaErrorCode;
import cn.dev33.satoken.exception.SaTokenException;
import com.cuii.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public Result<String> serverException(ServerException e){
        log.error("已知异常:{}",e.getMsg());
        return Result.error(e.getMsg());
    }

    @ExceptionHandler(SaTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<String> saTokenException(SaTokenException e){
        log.info("用户权限相关错误:{}",e.getMessage());
        return Result.error("用户未登录,或登录状态已过期!");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e){
        e.printStackTrace();
        return Result.error("操作异常!");
    }
}
