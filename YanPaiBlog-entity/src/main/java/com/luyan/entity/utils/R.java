package com.luyan.entity.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private R(ResultCodeEnum result, T data) {
        this(result.getCode(), result.getMessage(), data);
    }

    private R(ResultCodeEnum result) {
        this(result, null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(ResultCodeEnum.SUCCESS, data);
    }

    public static R<Object> okPairs(Pair ...pairs) {
        Map<String, Object> map = new HashMap<>() {
            {
                for (Pair pair: pairs) {
                    put(pair.getKey(), pair.getValue());
                }
            }
        };
        return R.ok(map);
    }

    public static R<Object> error(String message) {
        return new R<>(ResultCodeEnum.OTHER_ERROR.getCode(), message, null);
    }

    public static R<Object> error(ResultCodeEnum result) {
        return new R<>(result);
    }

    public static R<Object> error(Integer code, String message) {
        return new R<>(code, message, null);
    }

    @Getter
    @AllArgsConstructor
    public static class Pair {
        private String key;
        private Object value;

        public static Pair of(String key, Object value) {
            return new Pair(key, value);
        }
    }
}
