package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private Integer userId;
    private String title;
    private String picture;
    private String summary;
    private Integer categoryId;
    private Integer status;  // 状态：0-未发布，1-已发布

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
