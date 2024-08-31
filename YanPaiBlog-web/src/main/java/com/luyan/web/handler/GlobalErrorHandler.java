package com.luyan.web.handler;

import com.luyan.entity.exception.ServiceException;
import com.luyan.entity.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ServiceException.class)
    public R<Object> serviceExceptionHandler(ServiceException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Object> argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String result = allErrors.stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).collect(Collectors.joining(";"));
        return R.error(result);
    }
}
