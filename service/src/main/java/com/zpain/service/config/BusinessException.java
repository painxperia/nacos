package com.zpain.service.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangjun
 * @date 2022/1/13  16:19
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private boolean success;
    private String message;

    public BusinessException(String message) {
        this.success = false;
        this.message = message;
    }


}
