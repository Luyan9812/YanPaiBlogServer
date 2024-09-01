package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class Category {
    private Integer id;
    private String categoryName;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
