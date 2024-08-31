package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ArticleTag {
    private Integer id;
    private Integer articleId;
    private Integer tagId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
