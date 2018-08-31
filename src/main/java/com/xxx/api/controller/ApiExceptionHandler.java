package com.xxx.api.controller;

import com.xxx.api.exception.ApiStatus;
import com.xxx.api.exception.UserException;
import com.xxx.api.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Api异常增强类
 * 捕获Exception封装到ApiResponse中返回
 * @author zhangst
 * @version 2018-5-2
 */
@Slf4j
@ResponseBody
@ControllerAdvice("com.xxx.api.controller")
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ApiResponse userException(UserException exception){
        return new ApiResponse(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse lackNecessaryParameter(MissingServletRequestParameterException exception){
        return new ApiResponse(ApiStatus.LACK_PARAMETER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse parameterNotValid(ConstraintViolationException exception){
        return new ApiResponse(ApiStatus.INVALID_PARAMETER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse parameterNotValid(MethodArgumentNotValidException exception){
        return new ApiResponse(ApiStatus.INVALID_PARAMETER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ApiResponse dateTimeParseError(DateTimeParseException exception){
        return new ApiResponse(ApiStatus.DATETIME_PARSE_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse noSuchRequest(NoHandlerFoundException exception){
        return new ApiResponse(ApiStatus.NOT_SUCH_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public ApiResponse ioException(IOException exception){
        log.error("IO异常", exception);
        return new ApiResponse(ApiStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse runtimeException(RuntimeException exception){
        log.error("运行时异常", exception);
        return new ApiResponse(ApiStatus.INTERNAL_SERVER_ERROR);
    }

}
