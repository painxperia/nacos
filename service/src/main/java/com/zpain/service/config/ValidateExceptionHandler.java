package com.zpain.service.config;

import com.zpain.service.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjun
 * @date 2021/12/29  13:08
 */
@ControllerAdvice
public class ValidateExceptionHandler {

    /**
     * do requestBody
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult br = e.getBindingResult();
        StringBuilder s = new StringBuilder();
        if (br.hasErrors()) {
            List<ObjectError> allErrors = br.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                allErrors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    s.append(fieldError.getDefaultMessage());
                });
            }
        }
        return Result.falseResult(s.toString());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(BindException e) {
        String str = e.getBindingResult().getAllErrors().stream()
                .map(p -> {
                    FieldError fieldError = (FieldError) p;
                    return fieldError.getDefaultMessage();
                }).collect(Collectors.joining());
        return Result.falseResult(str);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(MissingServletRequestParameterException e) {
        String message = e.getMessage();
        return Result.falseResult(message);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<?> businessException(BusinessException e) {
        return Result.falseResult(e.getMessage());
    }
}
