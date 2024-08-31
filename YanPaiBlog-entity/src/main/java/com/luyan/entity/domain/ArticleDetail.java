package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ArticleDetail {
    private Integer id;
    private Integer articleId;
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Article article;
}
