package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Tag {
    private Integer id;
    private String tagName;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
