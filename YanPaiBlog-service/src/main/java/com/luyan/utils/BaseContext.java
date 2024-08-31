package com.luyan.utils;

public class BaseContext {
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void setCurrentId(Integer uid) {
        local.set(uid);
    }

    public static Integer getCurrentId() {
        return local.get();
    }
}
