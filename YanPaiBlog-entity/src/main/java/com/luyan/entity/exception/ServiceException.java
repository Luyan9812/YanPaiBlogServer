package com.luyan.entity.exception;

import com.luyan.entity.utils.ResultCodeEnum;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ResultCodeEnum error) {
        this(error.getMessage());
    }
}
