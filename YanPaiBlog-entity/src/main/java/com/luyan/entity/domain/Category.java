package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;
    private String categoryName;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
