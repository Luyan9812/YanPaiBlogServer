package com.luyan.entity.exception;

import com.luyan.entity.utils.ResultCodeEnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private ResultCodeEnum error;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ResultCodeEnum error) {
        this.error = error;
    }
}
