package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"categoryId", "createTime", "updateTime"})
public class Tag {
    private Integer id;
    private String tagName;
    private Integer categoryId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
