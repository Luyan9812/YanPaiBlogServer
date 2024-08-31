package com.luyan.entity.utils;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(0, "请求成功"),

    NO_LOGIN(10, "未登录或登录信息已过期"),

    USERNAME_NOT_FOUND_ERROR(20, "用户名不存在"),
    USERNAME_EXISTS_ERROR(21, "用户名已存在"),
    PASSWORD_ERROR(22, "密码错误"),

    OTHER_ERROR(30, "其它错误"),
    UNKNOWN_ERROR(40, "未知错误");

    private final Integer code;
    private final String message;
}
